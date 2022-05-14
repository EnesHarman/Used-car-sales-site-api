package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.*;
import com.sahibinden.arac.dto.requests.AddCommentRequest;
import com.sahibinden.arac.model.Comment;
import com.sahibinden.arac.model.Customer;
import com.sahibinden.arac.model.Vehicle;
import com.sahibinden.arac.repository.CommentRepository;
import com.sahibinden.arac.service.constants.Messages;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private AppUserService appUserService;

    public CommentServiceImpl(CommentRepository commentRepository, AppUserService appUserService) {
        this.commentRepository = commentRepository;
        this.appUserService = appUserService;
    }

    @Override
    public Result addComment(long vehicleId, AddCommentRequest addCommentRequest) {
        long customerId = this.appUserService.getCustomerIdByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Comment comment = Comment.builder()
                .commentBody(addCommentRequest.getCommentBody())
                .customer(new Customer(customerId))
                .vehicle(new Vehicle(vehicleId))
                .build();
        this.commentRepository.save(comment);
        return new SuccessResult(Messages.COMMENT_ADDED);
    }

    @Override
    public DataResult<List<Comment>> getVehicleComments(long id) {
        return new SuccessDataResult<>(this.commentRepository.findAllByVehicle_VehicleId(id));
    }

    @Override
    @Transactional
    public Result deleteComment(long commentId) {
        long commentOwnerId = this.appUserService.getCustomerIdByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Optional<Comment> comment = this.commentRepository.findById(commentId);
        if (!comment.isPresent()){
            return new ErrorResult(Messages.COMMENT_NOT_FOUND);
        }
        this.commentRepository.delete(comment.get());
        if(comment.get().getCustomer().getCustomerId() != commentOwnerId){
            return new ErrorResult(Messages.NOT_AUTHORIZED_ACTION);
        }
        return new SuccessResult(Messages.COMMENT_DELETED);
    }
}

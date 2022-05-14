package com.sahibinden.arac.controller;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.requests.AddCommentRequest;
import com.sahibinden.arac.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add/{vehicleId}")
    public ResponseEntity<String> addCommentToVehicle(@PathVariable long vehicleId, @RequestBody AddCommentRequest addCommentRequest){
        Result result = this.commentService.addComment(vehicleId,addCommentRequest);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId){
        Result result = this.commentService.deleteComment(commentId);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.internalServerError().body(result.getMessage());
    }
}

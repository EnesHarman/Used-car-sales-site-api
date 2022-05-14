package com.sahibinden.arac.service;

import com.sahibinden.arac.core.result.DataResult;
import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.requests.AddCommentRequest;
import com.sahibinden.arac.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Result addComment(long vehicleId, AddCommentRequest addCommentRequest);

    DataResult<List<Comment>> getVehicleComments(long id);

    Result deleteComment(long commentId);
}

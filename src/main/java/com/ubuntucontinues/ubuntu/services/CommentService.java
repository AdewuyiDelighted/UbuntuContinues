package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.AddCommentRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddCommentResponse;
import com.ubuntucontinues.ubuntu.dto.responses.CommentResponse;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

import java.util.List;

public interface CommentService {

    AddCommentResponse comment(AddCommentRequest request) throws PostNotExistException, UserExistException;
    List<CommentResponse> getAllCommentByPost(String postId) throws PostNotExistException;
}

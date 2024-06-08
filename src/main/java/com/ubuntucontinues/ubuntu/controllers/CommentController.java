package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.AddCommentRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddCommentResponse;
import com.ubuntucontinues.ubuntu.dto.responses.CommentResponse;
import com.ubuntucontinues.ubuntu.exceptions.CommentDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubuntu/comment")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;
    @PostMapping()
    public ResponseEntity<AddCommentResponse> comment(@RequestBody AddCommentRequest request) throws PostNotExistException, UserExistException {
        return new ResponseEntity<>(commentService.comment(request), HttpStatus.CREATED);
    }

    @GetMapping("/list/{postId}")
    public ResponseEntity<List<CommentResponse>> findAllPostComment(@PathVariable String postId) throws PostNotExistException {
        return new ResponseEntity<>(commentService.getAllCommentByPost(postId), HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> findCommentBy(@PathVariable String commentId) throws CommentDoesNotExistException {
        return new ResponseEntity<>(commentService.findBy(commentId), HttpStatus.OK);
    }



}

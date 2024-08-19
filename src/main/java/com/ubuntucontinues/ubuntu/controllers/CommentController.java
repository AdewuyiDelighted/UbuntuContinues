package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.AddCommentRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.exceptions.CommentDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubuntu/comment")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;
    @PostMapping()
    public ResponseEntity<ApiResponse<?>> comment(@RequestBody AddCommentRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, commentService.comment(request)), HttpStatus.CREATED);
        }catch (PostNotExistException | UserExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/list/{postId}")
    public ResponseEntity<ApiResponse<?>> findAllPostComment(@PathVariable String postId) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, commentService.getAllCommentByPost(postId)), HttpStatus.OK);
        }catch (PostNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponse<?>> findCommentBy(@PathVariable String commentId) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, commentService.findBy(commentId)), HttpStatus.OK);
        }catch (CommentDoesNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }



}

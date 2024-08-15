package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.CreatePostRequest;
import com.ubuntucontinues.ubuntu.dto.requests.LikePostRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdatePostRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ubuntu/post")
@AllArgsConstructor
@CrossOrigin("*")
public class PostController {
    private PostService postService;

    @PostMapping("/create_post")
    public ResponseEntity<ApiResponse<?>> post(@RequestPart(value = "image", required = false) MultipartFile multipartFile, @ModelAttribute CreatePostRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, postService.post(request, multipartFile)), HttpStatus.CREATED);
        }catch(UserExistException | IOException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String postId) {
        return new ResponseEntity<>(new ApiResponse<>(true, postService.delete(postId)), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<ApiResponse<?>> update(@RequestBody UpdatePostRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, postService.update(request)), HttpStatus.OK);
        }catch (PostNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> findAllPostBy(@PathVariable String userId){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, postService.getAllPostByUser(userId)), HttpStatus.FOUND);
        }catch (UserExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));

        }
    }

    @PostMapping("/like")
    public ResponseEntity<ApiResponse<?>> likePost(@RequestBody LikePostRequest request){
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, postService.likePost(request)), HttpStatus.ACCEPTED);
        }catch (PostNotExistException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }


    @GetMapping("/all_post")
    public ResponseEntity<?> getAllPost(){
        return new ResponseEntity<>(postService.getAllPost(),HttpStatus.OK);
    }
}

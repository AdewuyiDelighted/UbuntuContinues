package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.CreatePostRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdatePostRequest;
import com.ubuntucontinues.ubuntu.dto.responses.*;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ubuntu/post")
@AllArgsConstructor
@CrossOrigin("*")
public class PostController {
    private PostService postService;

    @PostMapping()
    public ResponseEntity<CreatePostResponse> post(
            @RequestPart(value = "image", required = false) MultipartFile multipartFile,
            @ModelAttribute CreatePostRequest request
    ) throws UserExistException, IOException {
        return new ResponseEntity<>(postService.post(request, multipartFile), HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<DeletePostResponse> delete(@PathVariable String postId) {
        return new ResponseEntity<>(postService.delete(postId), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<UpdatePostResponse> update(@RequestBody UpdatePostRequest request) throws PostNotExistException {
        return new ResponseEntity<>(postService.update(request), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PostResponse>> findAllPostBy(@PathVariable String userId) throws UserExistException {
        return new ResponseEntity<>(postService.getAllPostByUser(userId), HttpStatus.FOUND);
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<LikePostResponse> likePost(@PathVariable String postId) throws PostNotExistException {
        return new ResponseEntity<>(postService.likePost(postId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all_post")
    public ResponseEntity<?> getAllPost(){
        return new ResponseEntity<>(postService.getAllPost(),HttpStatus.OK);
    }

}

//all_post

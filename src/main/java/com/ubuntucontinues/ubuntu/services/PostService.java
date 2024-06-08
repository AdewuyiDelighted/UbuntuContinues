package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Post;
import com.ubuntucontinues.ubuntu.dto.requests.CreatePostRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdatePostRequest;
import com.ubuntucontinues.ubuntu.dto.responses.*;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    CreatePostResponse post(CreatePostRequest request, MultipartFile file) throws UserExistException, IOException;
    DeletePostResponse delete(String postId);
    UpdatePostResponse update(UpdatePostRequest request) throws PostNotExistException;
    Post findPostById(String postId) throws PostNotExistException;
    List<PostResponse> getAllPostByUser(String userId) throws UserExistException;
    LikePostResponse likePost(String postId) throws PostNotExistException;
    List<GetAllPostResponse> getAllPost();




}

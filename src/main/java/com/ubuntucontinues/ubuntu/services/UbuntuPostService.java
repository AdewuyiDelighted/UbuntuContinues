package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Post;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.PostRepository;
import com.ubuntucontinues.ubuntu.dto.requests.CreatePostRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdatePostRequest;
import com.ubuntucontinues.ubuntu.dto.responses.*;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
public class UbuntuPostService implements PostService{

    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private PostRepository repository;


    @Override
    public CreatePostResponse post(CreatePostRequest request, MultipartFile file) throws UserExistException, IOException {
       User user = userService.findBY(request.getUserId());
       Post post = new Post();
       post.setBody(request.getBody());
       if (file != null) {
           String imageUrl = cloudinaryService.uploadImage(file).getImageUrl();
           post.setImage(imageUrl);
       }
       post.setTitle(request.getTitle());
       post.setUser(user);
       Post savedPost = repository.save(post);
       CreatePostResponse response = new CreatePostResponse();
       response.setId(savedPost.getId());
       response.setMessage(CREATE_POST_RESPONSE);
       return response;
    }

    @Override
    public DeletePostResponse delete(String postId) {
        repository.deleteById(postId);
        DeletePostResponse response = new DeletePostResponse();
        response.setMessage(AppUtils.DELETE_POST_MESSAGE);
        return response;
    }

    @Override
    public UpdatePostResponse update(UpdatePostRequest request) throws PostNotExistException {
        Post post = findPostById(request.getPostId());
        if (request.getTitle() != null && !request.getTitle().isEmpty()){
            post.setTitle(request.getTitle());
        }
        if (request.getBody() != null){
            post.setBody(request.getBody());
        }
        Post savedPost = repository.save(post);
        UpdatePostResponse response = new UpdatePostResponse();
        response.setPostId(savedPost.getId());
        response.setMessage(UPDATE_POST_RESPONSE);
        return response;
    }


    @Override
    public Post findPostById(String postId) throws PostNotExistException {
        return repository.findById(postId).orElseThrow(() ->
                new PostNotExistException(POST_NOT_EXIST));
    }

    @Override
    public List<PostResponse> getAllPostByUser(String userId) throws UserExistException {
        return repository.findAllPostByUser(userService.findBY(userId))
                .stream().map(PostResponse::new).toList();
    }

    @Override
    public LikePostResponse likePost(String postId) throws PostNotExistException {
        Post post = findPostById(postId);
        post.setNumberOfLike(post.getNumberOfLike() + 1L);
        Post savedPost = repository.save(post);
        LikePostResponse response = new LikePostResponse();
        response.setMessage(AppUtils.LIKED_POST_RESPONSE);
        response.setPostId(savedPost.getId());
        return response;
    }


}

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Comment;
import com.ubuntucontinues.ubuntu.data.models.Post;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.CommentRepository;
import com.ubuntucontinues.ubuntu.dto.requests.AddCommentRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddCommentResponse;
import com.ubuntucontinues.ubuntu.dto.responses.CommentResponse;
import com.ubuntucontinues.ubuntu.exceptions.CommentDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.PostNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.COMMENT_NOT_FOUND;
import static com.ubuntucontinues.ubuntu.util.AppUtils.COMMENT_SAVED_RESPONSE;

@Service
@AllArgsConstructor
public class UbuntuCommentService implements CommentService{
    private PostService postService;
    private UserService userService;
    private CommentRepository repository;

    @Override
    public AddCommentResponse comment(AddCommentRequest request) throws PostNotExistException, UserExistException {
        Post post = postService.findPostById(request.getPostId());
        User user = userService.findBY(request.getUserId());
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = repository.save(comment);
        AddCommentResponse response = new AddCommentResponse();
        response.setId(savedComment.getId());
        response.setMessage(COMMENT_SAVED_RESPONSE);
        return response;
    }

    @Override
    public List<CommentResponse> getAllCommentByPost(String postId) throws PostNotExistException {
        return repository.findAllCommentByPost(postService.findPostById(postId)).stream()
                .map(CommentResponse::new).toList();
    }

    @Override
    public CommentResponse findBy(String commentId) throws CommentDoesNotExistException {
        return repository.findById(commentId)
                .stream()
                .map(CommentResponse::new)
                .findFirst()
                .orElseThrow(()-> new CommentDoesNotExistException(COMMENT_NOT_FOUND));
    }
}

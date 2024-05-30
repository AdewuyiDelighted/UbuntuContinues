package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.Comment;
import com.ubuntucontinues.ubuntu.data.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllCommentByPost(Post postById);

}

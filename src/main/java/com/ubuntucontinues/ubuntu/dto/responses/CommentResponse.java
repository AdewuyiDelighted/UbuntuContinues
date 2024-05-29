package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private String content;
    private String userEmail;
    private String id;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
        this.userEmail = comment.getUser().getEmail();
        this.id = comment.getId();
    }
}

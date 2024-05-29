package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
    private String id;
    private String title;
    private String body;
    private String image;
    private String userEmail;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.image = post.getImage();
        this.userEmail = post.getUser().getEmail();
    }
}

package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class GetAllPostResponse {
    private String id;
    private String title;
    private String body;
    private String image;
    private Map<String, Boolean> likes;
    private String email;

    public GetAllPostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.email = post.getUser().getEmail();
        this.body = post.getBody();
        this.image = post.getImage();
        this.likes = post.getLikes();
    }
}
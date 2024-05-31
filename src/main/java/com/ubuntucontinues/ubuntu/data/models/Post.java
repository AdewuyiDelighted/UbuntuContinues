package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Post {
    @Id
    private String id;
    private String title;
    private String body;
    private String image;
    @DBRef
    private User user;
    private Long numberOfLikes = 0L;
}

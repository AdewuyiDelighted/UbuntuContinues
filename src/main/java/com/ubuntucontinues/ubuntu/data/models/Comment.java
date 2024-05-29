package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
public class Comment {
    @Id
    private String id;
    private String content;
    @DBRef
    private User user;
    @DBRef
    private Post post;
}

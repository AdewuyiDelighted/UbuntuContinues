package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Document
@Getter
@Setter
@ToString
public class Post {
    @Id
    private String id;
    private String title;
    private String body;
    private String image;
    @DBRef
    private User user;
    private Map<String, Boolean> likes = new HashMap<>();
    @CreatedDate
    private LocalDateTime createdAt;
}

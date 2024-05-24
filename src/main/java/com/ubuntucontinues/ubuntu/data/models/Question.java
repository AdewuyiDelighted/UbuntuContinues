package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document
public class Question {
    @Id
    private String id;
    private String title;
    private String body;
    private User askedBy;
    private LocalDateTime datedPosted;


}

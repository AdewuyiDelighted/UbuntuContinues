package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;


@Document
@Getter
@Setter
@ToString
public class Question {
    @Id
    private String id;
    private String title;
    private String body;
    private String codeSnippet;
    @DBRef
    private User user;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private List<String> tags;
    private LocalDate createdAt=LocalDate.now();
}

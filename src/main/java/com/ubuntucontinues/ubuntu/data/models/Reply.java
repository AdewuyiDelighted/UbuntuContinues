package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document
public class Reply {
    @Id
    private String id;
    private String replyBody;
    private LocalDateTime createdAt = LocalDateTime.now();
    private User replyBy;
    private Question replyTo;

}

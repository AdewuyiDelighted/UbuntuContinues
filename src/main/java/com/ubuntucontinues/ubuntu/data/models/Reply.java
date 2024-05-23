package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
public class Reply {
    private String id;
    private Question replyTo;
    private String replyBody;
    private User replyBy;
    private LocalDateTime createdAt;
}

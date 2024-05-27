package com.ubuntucontinues.ubuntu.data.models;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@Document
public class Notification {
    @Id
    private String id;
    private String message;
    private String body;
    private LocalDateTime createdAt;
    private String title;
    private User user;
}


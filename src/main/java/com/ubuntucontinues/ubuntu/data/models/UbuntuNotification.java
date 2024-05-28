package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document
public class UbuntuNotification {
    private String message;
    private String title;
    private LocalDateTime createdAt = LocalDateTime.now();
    private User user;
}

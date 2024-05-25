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
    private String body;
    private LocalDateTime ReplyTime = LocalDateTime.now();
    private User user;
    private Question question;

}

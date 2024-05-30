package com.ubuntucontinues.ubuntu.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document
@Setter
@Getter
@Builder
@ToString
public class ChatMessage {
    private String id;
    private String chatMessageId;
    private String sendId;
    private String recipientId;
    private String content;
    private OffsetDateTime dateSent = OffsetDateTime.now();

}

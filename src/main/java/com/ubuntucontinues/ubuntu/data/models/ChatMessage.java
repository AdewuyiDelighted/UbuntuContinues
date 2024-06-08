package com.ubuntucontinues.ubuntu.data.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ChatMessage {
    private String id;
    private String chatMessageId;
    private String sendId;
    private String recipientId;
    private String content;
    private OffsetDateTime dateSent = OffsetDateTime.now();

    public ChatMessage(String id, String sendId,  String recipientId, String content) {
        this.chatMessageId = id;
        this.sendId = sendId;
        this.recipientId = recipientId;
        this.content = content;
    }
    @JsonCreator
    public ChatMessage(@JsonProperty("sendId") String sendId,
                       @JsonProperty("recipientId") String recipientId,
                       @JsonProperty("content") String content){
        this.sendId = sendId;
        this.recipientId = recipientId;
        this.content = content;
    }

}

package com.ubuntucontinues.ubuntu.data.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.ubuntucontinues.ubuntu.data.enums.MessageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

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
    private MessageStatus status= MessageStatus.UNREAD;
//    @JsonDeserialize(using = OffsetTimeDeserializer.class)
//    private OffsetDateTime dateSent = OffsetDateTime.now();
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonIgnore
    private LocalDate dateSent = LocalDate.now();

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

package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SendMessageRequest {
    private String sendId;
    private String recipientId;
    private String content;

    public SendMessageRequest(String sendId, String recipientId, String content){
        this.sendId = sendId;
        this.recipientId = recipientId;
        this.content = content;
    }
}

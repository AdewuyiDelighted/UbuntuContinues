package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class SendMessageResponse {
    private String sendId;
    private String recipientId;
    private String message;
    private String roomId;

    public SendMessageResponse(ChatMessage chatMessage) {
        this.message = chatMessage.getContent();
        this.sendId = chatMessage.getSendId();
        this.recipientId = chatMessage.getRecipientId();
        this.roomId = chatMessage.getChatMessageId();
    }
}

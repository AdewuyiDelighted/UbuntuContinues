package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetrieveChatRoomRequest {
    private String sender;
    private String recipient;

    public RetrieveChatRoomRequest(String senderEmail, String recipientEmail) {
        this.sender = senderEmail;
        this.recipient = recipientEmail;
    }
}

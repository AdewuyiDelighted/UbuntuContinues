package com.ubuntucontinues.ubuntu.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChatRoomResponse {
    private String sender_recipient_id;
    private String recipient_sender_id;
    private String message;
}

package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitializeChatRoomRequest {
    private String sender_email;
    private String recipient_email;
}

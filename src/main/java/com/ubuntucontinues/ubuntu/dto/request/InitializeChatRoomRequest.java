package com.ubuntucontinues.ubuntu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitializeChatRoomRequest {
    private String sender_email;
    private String recipient_email;
}

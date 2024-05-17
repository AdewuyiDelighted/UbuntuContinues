package com.ubuntucontinues.ubuntu.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecodeChatRoomResponse {
    private String sender_email;
    private String recipient_email;
}

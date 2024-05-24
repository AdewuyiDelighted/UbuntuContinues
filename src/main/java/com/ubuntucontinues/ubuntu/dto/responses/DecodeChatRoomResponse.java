package com.ubuntucontinues.ubuntu.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DecodeChatRoomResponse {
    private String sender_email;
    private String recipient_email;
}

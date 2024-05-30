package com.ubuntucontinues.ubuntu.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SendMessageResponse {
    private String sendId;
    private String recipientId;
    private String message;
    private String roomId;
}

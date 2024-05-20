package com.ubuntucontinues.ubuntu.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMessageResponse {
    private String sendId;
    private String recipientId;
    private String message;
}

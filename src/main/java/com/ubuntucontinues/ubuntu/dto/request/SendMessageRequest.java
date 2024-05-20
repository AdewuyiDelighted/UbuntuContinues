package com.ubuntucontinues.ubuntu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMessageRequest {
    private String sendId;
    private String recipientId;
    private String content;
}

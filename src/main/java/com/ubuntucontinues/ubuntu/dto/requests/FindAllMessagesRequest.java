package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindAllMessagesRequest {
    private String sendId;
    private String recipientId;
}

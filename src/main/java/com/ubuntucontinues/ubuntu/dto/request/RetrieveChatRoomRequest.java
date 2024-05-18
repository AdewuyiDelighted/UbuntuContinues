package com.ubuntucontinues.ubuntu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrieveChatRoomRequest {
    private String sender;
    private String recipient;
}

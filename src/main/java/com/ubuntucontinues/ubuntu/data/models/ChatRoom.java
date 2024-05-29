package com.ubuntucontinues.ubuntu.data.models;

import com.ubuntucontinues.ubuntu.data.enums.RoomState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
public class ChatRoom {
    @Id
    private String chat_id;
    private String senderEmail;
    private String recipientEmail;
    private RoomState status = RoomState.DEACTIVATED;

}

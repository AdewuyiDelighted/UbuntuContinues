package com.ubuntucontinues.ubuntu.dto.requests;

import com.ubuntucontinues.ubuntu.data.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class SendBulkNotificationRequest {
    private String message;
    private String title;
    List<User> users;

}

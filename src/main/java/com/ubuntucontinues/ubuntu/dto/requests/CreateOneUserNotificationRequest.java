package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateOneUserNotificationRequest {
    private String title;
    private String body;
    private String userId;

}

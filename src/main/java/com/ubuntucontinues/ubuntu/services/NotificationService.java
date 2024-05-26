package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.CreateOneUserNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateOneUserNotificationResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

public interface  NotificationService {
    CreateOneUserNotificationResponse sendOneUserNotification(CreateOneUserNotificationRequest createOneUserNotificationRequest) throws UserExistException;

}
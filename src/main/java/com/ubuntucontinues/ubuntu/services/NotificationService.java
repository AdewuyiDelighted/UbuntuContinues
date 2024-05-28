package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.SendBulkNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendBulkNotificationResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.dto.requests.CreateOneUserNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateOneUserNotificationResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

import java.util.List;

public interface NotificationService {
    SendBulkNotificationResponse notifyAllUsers(SendBulkNotificationRequest request);

    List<SendBulkNotificationResponse> findAllNotification(User first);
    CreateOneUserNotificationResponse sendOneUserNotification(CreateOneUserNotificationRequest createOneUserNotificationRequest) throws UserExistException;

}

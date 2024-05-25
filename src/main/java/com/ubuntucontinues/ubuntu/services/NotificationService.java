package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.SendBulkNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendBulkNotificationResponse;

import java.util.List;

public interface NotificationService {
    SendBulkNotificationResponse notifyAllUsers(SendBulkNotificationRequest request);

    List<SendBulkNotificationResponse> findAllNotification(User first);

}

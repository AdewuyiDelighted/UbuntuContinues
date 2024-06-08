package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;

import java.util.List;

public interface ScheduleSendNotificationService {
    void scheduleTask();
    void sendLoginEmail(List<User> users);


}

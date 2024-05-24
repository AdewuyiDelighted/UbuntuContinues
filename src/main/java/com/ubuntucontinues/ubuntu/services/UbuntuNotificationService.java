package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Notification;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.NotificationRepository;
import com.ubuntucontinues.ubuntu.dto.requests.CreateOneUserNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateOneUserNotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ubuntucontinues.ubuntu.util.AppUtils.NOTIFICATION_MESSAGE;

@Service
@AllArgsConstructor
public class UbuntuNotificationService implements NotificationService {
    private NotificationRepository notificationRepository;
    private UserService userService;

    @Override
    public CreateOneUserNotificationResponse sendOneUserNotification(CreateOneUserNotificationRequest createOneUserNotificationRequest) {
//        User user = userService.
        Notification notification = Notification.builder()
                .title(createOneUserNotificationRequest.getTitle())
                .body(createOneUserNotificationRequest.getBody())
//                .user(user)
                .build();
        String messageBody = NOTIFICATION_MESSAGE(createOneUserNotificationRequest.getTitle(), createOneUserNotificationRequest.getBody());

        CreateOneUserNotificationResponse createOneUserNotificationResponse = new CreateOneUserNotificationResponse();
        createOneUserNotificationResponse.setBody(messageBody);
        createOneUserNotificationResponse.setMessage("Notification Sent Successfully");

        notificationRepository.save(notification);
        return createOneUserNotificationResponse;
    }

}

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.enums.AccountState;
import com.ubuntucontinues.ubuntu.data.models.UbuntuNotification;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.NotificationRepository;
import com.ubuntucontinues.ubuntu.dto.requests.SendBulkNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendBulkNotificationResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import static com.ubuntucontinues.ubuntu.util.AppUtils.NOTIFICATION_MESSAGE;
import com.ubuntucontinues.ubuntu.dto.requests.CreateOneUserNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateOneUserNotificationResponse;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UbuntuNotificationService implements NotificationService{
        private NotificationRepository notificationRepository;
        private UserService userService;
        private ModelMapper modelMapper;
    @Override
    public SendBulkNotificationResponse notifyAllUsers(SendBulkNotificationRequest request) {
            List<User> usersToNotify = validatedUsers(request.getUsers());
        SendBulkNotificationResponse response = new SendBulkNotificationResponse();
        notifyUsers(usersToNotify,request);
        response.setReply("Notification sent");
        return response;
    }

    @Override
    public List<SendBulkNotificationResponse> findAllNotification(User user) {

        return notificationRepository.findAllByUser(user)
                .stream()
                .map(ubuntuNotification -> modelMapper.map(ubuntuNotification,SendBulkNotificationResponse.class)).toList()
                ;
    }

    private void notifyUsers(List<User> usersToNotify,SendBulkNotificationRequest request) {
        for(User user: usersToNotify){
            UbuntuNotification notification = modelMapper.map(request,UbuntuNotification.class);
            notification.setUser(user);
            notificationRepository.save(notification);

        }
    }


    private List<User> validatedUsers(List<User> users) {
        if (users == null) {
            return Collections.emptyList();
        }

        return users.stream()
                .filter(Objects::nonNull)
                .filter(user -> user.getAccountState().equals(AccountState.ACTIVATED))
                .collect(Collectors.toList());
    }
    @Override
    public CreateOneUserNotificationResponse sendOneUserNotification(CreateOneUserNotificationRequest createOneUserNotificationRequest) throws UserExistException {
        User user = userService.findBY(createOneUserNotificationRequest.getUserId());
        Notification notification = Notification.builder()
                .title(createOneUserNotificationRequest.getTitle())
                .body(createOneUserNotificationRequest.getBody())
                .user(user)
                .build();
        String messageBody = NOTIFICATION_MESSAGE(createOneUserNotificationRequest.getTitle(), createOneUserNotificationRequest.getBody());

        CreateOneUserNotificationResponse createOneUserNotificationResponse = new CreateOneUserNotificationResponse();
        createOneUserNotificationResponse.setBody(messageBody);
        createOneUserNotificationResponse.setMessage("Notification Sent Successfully");

        notificationRepository.save(notification);
        return createOneUserNotificationResponse;
    }



}

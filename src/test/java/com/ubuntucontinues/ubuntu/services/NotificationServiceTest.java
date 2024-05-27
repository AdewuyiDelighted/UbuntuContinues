package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.CreateOneUserNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateOneUserNotificationResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PasswordGeneratorServices passwordGeneratorServices;


    @Test
    public void testThatNewNotificationCanBeCreatedForAUser() throws UserExistException {
        CreateOneUserNotificationRequest createOneUserNotificationRequest = new CreateOneUserNotificationRequest();
        createOneUserNotificationRequest.setTitle("New Notification");
        createOneUserNotificationRequest.setBody("Your friend post a new question");
//        createOneUserNotificationRequest.setUserId();
        CreateOneUserNotificationResponse createOneUserNotificationResponse = notificationService.sendOneUserNotification(createOneUserNotificationRequest);
        System.out.println("new password " +passwordGeneratorServices.getPassword());
        assertThat(createOneUserNotificationResponse.getMessage(), is("Notification Sent Successfully"));
    }

    @Test public void testTheNotificationsBelongingToAllUsers(){
//        String userId = ""
//        assertEquals(1,notificationService.findAllNotificationBelongingToUser())

    }

}

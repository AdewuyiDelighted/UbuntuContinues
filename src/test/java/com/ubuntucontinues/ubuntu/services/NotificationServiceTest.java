package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.CreateOneUserNotificationRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateOneUserNotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
public class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PasswordGeneratorServices passwordGeneratorServices;


    @Test
    public void testThatNewNotificationCanBeCreatedForAUser() {
        CreateOneUserNotificationRequest createOneUserNotificationRequest = new CreateOneUserNotificationRequest();
        CreateOneUserNotificationResponse createOneUserNotificationResponse = notificationService.sendOneUserNotification(createOneUserNotificationRequest);
        System.out.println("new password " +passwordGeneratorServices.getPassword());
        assertThat(createOneUserNotificationResponse.getMessage(), is("Notification Sent Successfully"));
//        assertThat(createOneUserNotificationResponse.getBody(),is(createOneUserNotificationRequest.getBody()));
    }

}

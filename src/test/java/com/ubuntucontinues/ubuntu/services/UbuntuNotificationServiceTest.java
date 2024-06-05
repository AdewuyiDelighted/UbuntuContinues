//package com.ubuntucontinues.ubuntu.services;
//
//import com.ubuntucontinues.ubuntu.dto.requests.SendBulkNotificationRequest;
//import com.ubuntucontinues.ubuntu.dto.responses.SendBulkNotificationResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class UbuntuNotificationServiceTest {
//    @Autowired
//    private NotificationService notificationService;
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void testThatCanSendBulkNotification(){
//        SendBulkNotificationRequest request = new SendBulkNotificationRequest();
//        request.setTitle("New event");
//        request.setMessage("Theres a new event going on");
//        request.setUsers(userService.getAllUsers());
//        SendBulkNotificationResponse response = notificationService.notifyAllUsers(request);
//        assertNotNull(response);
////        assertNotNull(notificationService.findAllNotification(userService.getAllUsers().getLast()));
//        assertThat(notificationService.findAllNotification(userService.getAllUsers().getLast()).size()).isEqualTo(3);
//    }
//}

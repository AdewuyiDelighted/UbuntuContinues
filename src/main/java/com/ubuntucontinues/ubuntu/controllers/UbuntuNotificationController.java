package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.SendBulkNotificationRequest;
import com.ubuntucontinues.ubuntu.services.UbuntuNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")

public class UbuntuNotificationController {
    private UbuntuNotificationService ubuntuNotificationService;
    @PostMapping("notify_all_users")

    public ResponseEntity<?> notifyAllUsers(@RequestBody SendBulkNotificationRequest request){
        return ResponseEntity.ok(ubuntuNotificationService.notifyAllUsers(request));
    }



}

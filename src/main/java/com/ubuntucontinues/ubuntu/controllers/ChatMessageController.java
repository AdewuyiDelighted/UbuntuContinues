package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendMessageResponse;
import com.ubuntucontinues.ubuntu.services.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ubuntu/chatMessage")
@AllArgsConstructor
@CrossOrigin("*")
public class ChatMessageController {
    private ChatMessageService chatMessageService;

    @GetMapping("/getAllMessages")
    public ResponseEntity<?> getRecentChats(String senderId) {
        return new ResponseEntity<>(chatMessageService.findRecentlyChats(senderId), HttpStatus.FOUND);
    }
}


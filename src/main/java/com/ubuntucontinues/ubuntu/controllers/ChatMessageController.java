package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.services.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubuntu/chatMessage")
@AllArgsConstructor
@CrossOrigin("*")
public class ChatMessageController {
    private ChatMessageService chatMessageService;

    @GetMapping("/getAllMessages")
    public ResponseEntity<?> getRecentChatsEmail(@PathVariable  String senderId) {
        return new ResponseEntity<>(chatMessageService.findRecentlyChats(senderId), HttpStatus.OK);
    }
}


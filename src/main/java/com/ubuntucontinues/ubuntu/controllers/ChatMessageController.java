package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
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

    @GetMapping("/getAllRecipient/{senderId}")
    public ResponseEntity<?> getRecentChatUsers(@PathVariable String senderId) {
        return new ResponseEntity<>(chatMessageService.findRecentlyChats(senderId),HttpStatus.OK);
    }

    @PostMapping("/messages")
    public ResponseEntity<?> chatMessages(@RequestBody FindAllMessagesRequest request){
        return new ResponseEntity<>(chatMessageService.findAllMessagesBtwSendAndRecipient(request), HttpStatus.OK);
    }


}


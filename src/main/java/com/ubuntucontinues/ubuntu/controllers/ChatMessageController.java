package com.ubuntucontinues.ubuntu.controllers;


import com.ubuntucontinues.ubuntu.dto.request.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.response.SendMessageResponse;
import com.ubuntucontinues.ubuntu.services.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ubuntu/chatMessage")
@AllArgsConstructor
@CrossOrigin("*")
public class ChatMessageController {
    private ChatMessageService chatMessageService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    public void sendMessage(@Payload SendMessageRequest sendMessageRequest) {
        SendMessageResponse sendMessageResponse = chatMessageService.saveMessage(sendMessageRequest);
        simpMessagingTemplate.convertAndSendToUser(sendMessageResponse.getRecipientId(), "/queue/message", new SendMessageRequest());
    }

    @GetMapping("/getAllMessages")
    public ResponseEntity<List<SendMessageResponse>> findAllMessages(FindAllMessagesRequest findAllMessagesRequest) {
        return ResponseEntity.ok(chatMessageService.findAllMessagesBtwSendAndRecipient(findAllMessagesRequest));
    }

}

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

    @Autowired
    private ChatMessageService messageService;


    public void saveSocketMessage(ChatMessage message) {
        messageService.saveMessage(new SendMessageRequest(message.getSendId(), message.getRecipientId(), message.getContent()));
    }

}

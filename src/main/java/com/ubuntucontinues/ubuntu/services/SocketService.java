package com.ubuntucontinues.ubuntu.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketService {

    @Autowired
    private ChatMessageService messageService;




    public void saveSocketMessage(SocketIOClient client, ChatMessage message) {
        SendMessageResponse response = messageService.saveMessage(new SendMessageRequest(message.getSendId(), message.getRecipientId(), message.getContent()));
        sendSocketMessage(client, message, response.getRoomId());
        System.out.println(response);
    }

    private void sendSocketMessage(SocketIOClient client, ChatMessage message, String roomId) {
        for (SocketIOClient socketIOClient: client.getNamespace().getRoomOperations(roomId).getClients()){
            if (!socketIOClient.getSessionId().equals(client.getSessionId())){
                socketIOClient.sendEvent("read_message", message);
            }
        }
    }


}

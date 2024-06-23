package com.ubuntucontinues.ubuntu.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import com.ubuntucontinues.ubuntu.dto.responses.SendMessageResponse;
import com.ubuntucontinues.ubuntu.services.SocketService;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@CrossOrigin("*")
@Slf4j
public class SocketIoConfig {
    @Value("${socket.host}")
    private String socketHost;
    @Value("${socket.port}")
    private int socketPort;
    private final ConcurrentHashMap<String, String> connectedUser = new ConcurrentHashMap<>();
    private final SocketService socketService;
    private SocketIOServer server;
    public SocketIoConfig(SocketService socketService){
        this.socketService = socketService;
    }

    @Bean
    public SocketIOServer socketIOServer(){
        Configuration configuration = new Configuration();
        configuration.setPort(socketPort);
        configuration.setHostname(socketHost);
        this.server = new SocketIOServer(configuration);
        server.start();
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", ChatMessage.class, onMessage());
        server.addEventListener("register", String.class, addUser());
        return server;
    }

    private DataListener<String> addUser() {
        return ((socketIOClient, username, ackRequest) -> {
           log.info("Adding -> {}", username);
            connectedUser.put(username, socketIOClient.getSessionId().toString());
        });
    }


    private DataListener<ChatMessage> onMessage() {
        return (socketIOClient, message, ackSender) -> {
            log.info(message.toString());
            String socketId = connectedUser.get(message.getRecipientId());
            socketService.saveSocketMessage(message);
            SendMessageResponse sendMessageResponse = new SendMessageResponse(message);
            if(socketId != null) server.getClient(UUID.fromString(socketId)).sendEvent("message", sendMessageResponse);
        };
    }

    private DisconnectListener onDisconnected() {
        return socketIOClient -> socketIOClient.getNamespace().getAllClients().forEach(data-> log.info("user disconnected "+data.getSessionId().toString()));
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }

    public ConnectListener onConnected(){
        return (socketIOClient) -> log.info("Connected user with session id of "+ socketIOClient.getSessionId().toString());
    }



}

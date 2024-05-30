package com.ubuntucontinues.ubuntu.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketIoConfig {
    @Value("${socket.host}")
    private String socketHost;
    @Value("${socket.port}")
    private int socketPort;
    private SocketIOServer server;
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
        return server;
    }

    private DataListener<ChatMessage> onMessage() {
        return (socketIOClient, message, ackSender) -> {
            log.info(message.toString());

        };
    }

    private DisconnectListener onDisconnected() {
        return socketIOClient -> {
            socketIOClient.getNamespace().getAllClients().stream().forEach(data-> {
                log.info("user disconnected "+data.getSessionId().toString());});
        };
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }

    public ConnectListener onConnected(){
        return (socketIOClient) ->{
            log.info("Connected user with session id of "+ socketIOClient.getSessionId().toString());
        };
    }



}

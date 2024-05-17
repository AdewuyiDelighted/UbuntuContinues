package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.request.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.response.InitializeChatRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ubuntucontinues.ubuntu.util.AppUtils.INITIATE_REQUEST_MESSAGE;

@Service
public class UbuntuChatRoomService implements ChatRoomService{

    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;


    @Override
    public InitializeChatRoomResponse initializeChatRoom(InitializeChatRoomRequest request) {
        String token = "localhost:8080/api/v1/chatroom/"+jwtService.createToken(request.getSender_email(), request.getRecipient_email());
        emailService.sendMessage(request.getSender_email(),
                INITIATE_REQUEST_MESSAGE(request.getSender_email(),
                        request.getRecipient_email(),
                        token),
                request.getRecipient_email());
        InitializeChatRoomResponse response = new InitializeChatRoomResponse();
        response.setMessage("Request has been sent to the sender to activate chat");
        return response;
    }
}

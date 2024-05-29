package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.ChatRoom;
import com.ubuntucontinues.ubuntu.data.repositories.ChatRoomRepository;
import com.ubuntucontinues.ubuntu.dto.requests.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.RetrieveChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;
import com.ubuntucontinues.ubuntu.dto.responses.CreateChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DecodeChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.responses.InitializeChatRoomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
@Slf4j
public class UbuntuChatRoomService implements ChatRoomService{

    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ChatRoomRepository chatRoomRepository;


    @Override
    public InitializeChatRoomResponse initializeChatRoom(InitializeChatRoomRequest request) {
        String token = "localhost:8080/api/v1/chatroom/"+jwtService.createToken(request.getSender_email(), request.getRecipient_email());
        emailService.sendMessage(new Sender(request.getSender_email(), request.getSender_email()),
                INITIATE_REQUEST_MESSAGE(request.getSender_email(), request.getRecipient_email(), token),
                List.of(new Recipient(request.getRecipient_email(), request.getRecipient_email())),
                REQUEST_MESSAGE);
        InitializeChatRoomResponse response = new InitializeChatRoomResponse();
        response.setMessage(INITIALIZE_REQUEST_MESSAGE);
        return response;
    }

    @Override
    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    @Override
    public CreateChatRoomResponse createChatRoom(String token) {
        DecodeChatRoomResponse response = jwtService.decodeToken(token, DecodeChatRoomResponse.class);

        ChatRoom sender_recipient_chatRoom = mapChatRoom(response.getSender_email(), response.getRecipient_email());

        ChatRoom recipient_sender_chatRoom = mapChatRoom(response.getRecipient_email(), response.getSender_email());

        chatRoomRepository.save(sender_recipient_chatRoom);

        chatRoomRepository.save(recipient_sender_chatRoom);

        CreateChatRoomResponse createChatRoomResponse = new CreateChatRoomResponse();
        createChatRoomResponse.setSender_recipient_id(sender_recipient_chatRoom.getChat_id());
        createChatRoomResponse.setRecipient_sender_id(recipient_sender_chatRoom.getChat_id());
        createChatRoomResponse.setMessage("Chat Room Created Successfully");
        return createChatRoomResponse;
    }

    @Override
    public Optional<String> getAChatRoomId(RetrieveChatRoomRequest request) {
        return chatRoomRepository.findChatRoomBySenderEmailAndRecipientEmail(request.getSender(), request.getRecipient())
                .map(ChatRoom::getChat_id)
                .or(Optional::empty);
    }

    @Override
    public List<ChatRoom> findAllChatSenderChatRoom(String sender) {
        return chatRoomRepository.findChatRoomBySenderEmailOrRecipientEmail(sender,sender);
    }

    private ChatRoom mapChatRoom(String sender, String recipient){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setChat_id(String.format(sender+"_"+recipient));
        chatRoom.setSenderEmail(sender);
        chatRoom.setRecipientEmail(recipient);
        return chatRoom;
    }

}

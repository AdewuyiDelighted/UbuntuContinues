package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.enums.RoomState;
import com.ubuntucontinues.ubuntu.data.models.ChatRoom;
import com.ubuntucontinues.ubuntu.data.repositories.ChatRoomRepository;
import com.ubuntucontinues.ubuntu.dto.requests.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.RetrieveChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;
import com.ubuntucontinues.ubuntu.dto.responses.ActivateChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.responses.CreateChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DecodeChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.responses.InitializeChatRoomResponse;
import com.ubuntucontinues.ubuntu.util.AppUtils;
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
        InitializeChatRoomResponse response = new InitializeChatRoomResponse();
        ChatRoom room = getAChatRoom(new RetrieveChatRoomRequest(request.getSender_email(), request.getRecipient_email()));
        if (room == null){
            sendFriendRequestAndCreateChatRoom(request, response);
        } else if (room.getStatus().equals(RoomState.DEACTIVATED)) {
            response.setMessage(INITIALIZE_REQUEST_MESSAGE);
            response.setActivated(false);
        }else {
            response.setMessage(INITIALIZE_REQUEST_MESSAGE);
            response.setActivated(true);
        }
        return response;
    }

    private void sendFriendRequestAndCreateChatRoom(InitializeChatRoomRequest request, InitializeChatRoomResponse response) {
        String token = jwtService.createToken(request.getSender_email(), request.getRecipient_email());
        String url = "localhost:8080/ubuntu/chatroom/activate?token="+token;
        emailService.sendMessage(new Sender(request.getSender_email(), request.getSender_email()),
                INITIATE_REQUEST_MESSAGE(request.getSender_email(), request.getRecipient_email(), url),
                List.of(new Recipient(request.getRecipient_email(), request.getRecipient_email())),
                REQUEST_MESSAGE);
        createChatRoom(token);
        response.setMessage(INITIALIZE_REQUEST_MESSAGE);
        response.setActivated(false);
    }

    private ChatRoom getAChatRoom(RetrieveChatRoomRequest retrieveChatRoomRequest) {
        return chatRoomRepository.findChatRoomBySenderEmailAndRecipientEmail(retrieveChatRoomRequest.getSender(), retrieveChatRoomRequest.getRecipient())
                .orElse(null);
    }



    @Override
    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    @Override
    public CreateChatRoomResponse createChatRoom(String token) {
        DecodeChatRoomResponse response = jwtService.decodeToken(token, DecodeChatRoomResponse.class);

        ChatRoom sender_recipient_chatRoom = mapChatRoom(response.getSender_email(), response.getRecipient_email());

        chatRoomRepository.save(sender_recipient_chatRoom);

        CreateChatRoomResponse createChatRoomResponse = new CreateChatRoomResponse();
        createChatRoomResponse.setSender_recipient_id(sender_recipient_chatRoom.getChat_id());
        createChatRoomResponse.setMessage(CHATROOM_CREATED_MESSAGE);
        return createChatRoomResponse;
    }

    @Override
    public Optional<String> getAChatRoomId(RetrieveChatRoomRequest request) {
        return chatRoomRepository.findChatRoomBySenderEmailAndRecipientEmail(request.getSender(), request.getRecipient())
                .map(ChatRoom::getChat_id)
                .or(Optional::empty);
    }

    @Override
    public ActivateChatRoomResponse activateChatRoom(String token) {
        DecodeChatRoomResponse decodeToken = jwtService.decodeToken(token, DecodeChatRoomResponse.class);
        ChatRoom room = getAChatRoom(new RetrieveChatRoomRequest(decodeToken.getSender_email(), decodeToken.getRecipient_email()));
        room.setStatus(RoomState.ACTIVATED);
        chatRoomRepository.save(room);
        ActivateChatRoomResponse response = new ActivateChatRoomResponse();
        response.setMessage(AppUtils.ACTIVATE_CHAT);
        return response;
    }

    private ChatRoom mapChatRoom(String sender, String recipient){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setChat_id(String.format(sender+"_"+recipient));
        chatRoom.setSenderEmail(sender);
        chatRoom.setRecipientEmail(recipient);
        return chatRoom;
    }
}

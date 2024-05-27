package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.ChatRoom;
import com.ubuntucontinues.ubuntu.data.repositories.ChatRoomRepository;
import com.ubuntucontinues.ubuntu.dto.requests.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.requests.RetrieveChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.responses.InitializeChatRoomResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomService chatRoomService;
//    @MockBean
    private ChatRoomRepository repository;


    @Test
    public void testThatCreateChatRoomId() {
        InitializeChatRoomRequest request = new InitializeChatRoomRequest();
        request.setSender_email("ojot630@gmail.com");
        request.setRecipient_email("deborahdelighted5@gmail.com");
        InitializeChatRoomResponse response = chatRoomService.initializeChatRoom(request);
        assertNotNull(response);
    }

    @Test
    public void testThatTwoChatRoomCanBeInitiatedOnceTheLinkIsClickOn(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJxdWl6LWFwcGxpY2F0aW9uIiwic3ViIjoiYWNjZXNzX3Rva2VuIiwic2VuZGVyX2VtYWlsIjoib2pvdDYzMEBnbWFpbC5jb20iLCJyZWNpcGllbnRfZW1haWwiOiJkZWJvcmFoZGVsaWdodGVkNUBnbWFpbC5jb20iLCJleHAiOjE3MTc2ODExODF9._7Exutg8-q7NjdlJPFqwkpel6-1J4SO3KS3EOzw1B4g";
        CreateChatRoomResponse response = chatRoomService.createChatRoom(token);
        assertNotNull(response);
        assertNotNull(response.getSender_recipient_id());
        assertNotNull(response.getRecipient_sender_id());
    }

    @Test
    public void testThatWeCanGetTheChatRoomOnceWeKnowTheSenderAndRecipient(){
        RetrieveChatRoomRequest request = new RetrieveChatRoomRequest();
        request.setSender("ojot630@gmail.com");
        request.setRecipient("deborahdelighted5@gmail.com");

        ChatRoom room = new ChatRoom();

        room.setSenderEmail(request.getSender());

        room.setRecipientEmail(request.getRecipient());

        room.setChat_id(request.getSender()+"_"+request.getRecipient());

        when(repository.findChatRoomBySenderEmailAndRecipientEmail(request.getSender(),
                request.getRecipient())).thenReturn(Optional.of(room));

        String id = chatRoomService.getAChatRoomId(request).get();
        assertNotNull(id);
        assertThat(id).isEqualTo(request.getSender()+"_"+request.getRecipient());
    }


}
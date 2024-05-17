package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.request.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.response.InitializeChatRoomResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChatRoomServiceTest {

    @Autowired
    private ChatRoomService chatRoomService;

    @Test
    public void testThatCreateChatRoomId() {
        InitializeChatRoomRequest request = new InitializeChatRoomRequest();
        request.setSender_email("ojot630@gmail.com");
        request.setRecipient_email("deborahdelighted5@gmail.com");
        InitializeChatRoomResponse response = chatRoomService.initializeChatRoom(request);
        assertNotNull(response);
    }


}
package com.ubuntucontinues.ubuntu.services;


import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.data.repositories.ChatRoomRepository;
import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SendMessageResponse;
import com.ubuntucontinues.ubuntu.dto.responses.RecentChats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class ChatMessageServiceTest {
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatRoomRepository chatRoomRepository;


    @Test
    public void testThatUserCanSendMessage() {
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setSendId("ojot630@gmail.com");
        sendMessageRequest.setRecipientId("deborahdelighted5@gmail.com");
        sendMessageRequest.setContent("Hello,Thanks for accepting my request");
        SendMessageResponse sendMessageResponse = chatMessageService.saveMessage(sendMessageRequest);
        assertThat(sendMessageResponse.getMessage(), is("Message Sent Successfully"));

        FindAllMessagesRequest findAllMessagesRequest = new FindAllMessagesRequest();
        findAllMessagesRequest.setSendId("ojot630@gmail.com");
        findAllMessagesRequest.setRecipientId("deborahdelighted5@gmail.com");
        assertEquals(2, chatMessageService.findAllMessagesBtwSendAndRecipient(findAllMessagesRequest).size());

    }

    @Test
    public void testThatAllRecentlyChatOfAUserCanBeFound() {
        System.out.println(chatRoomRepository.findChatRoomBySenderEmailAndRecipientEmail("ojot630@gmail.com","ojot630@gmail.com"));
        List<RecentChats> recentChats = chatMessageService.findRecentlyChats("ojot630@gmail.com");
        assertEquals(2,recentChats.size());
    }
}

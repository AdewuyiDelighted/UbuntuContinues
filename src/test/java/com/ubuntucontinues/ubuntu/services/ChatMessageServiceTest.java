package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.response.SendMessageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class ChatMessageServiceTest {
    @Autowired
    private ChatMessageService chatMessageService;


    @Test
    public void testThatUserCanSendMessage(){
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setSendId("ojot630@gmail.com");
        sendMessageRequest.setRecipientId("deborahdelighted5@gmail.com");
        sendMessageRequest.setContent("Hello,Thanks for accepting my request");
        SendMessageResponse sendMessageResponse = chatMessageService.saveMessage(sendMessageRequest);
        assertThat(sendMessageResponse.getMessage(),is("Message Sent Successfully"));

        FindAllMessagesRequest findAllMessagesRequest  = new FindAllMessagesRequest();
        findAllMessagesRequest.setSendId("ojot630@gmail.com");
        findAllMessagesRequest.setRecipientId("deborahdelighted5@gmail.com");
        assertEquals(2,chatMessageService.findAllMessagesBtwSendAndRecipient(findAllMessagesRequest).size());

    }
}

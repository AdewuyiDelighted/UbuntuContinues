package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import com.ubuntucontinues.ubuntu.data.repositories.ChatMessageRepository;
import com.ubuntucontinues.ubuntu.dto.request.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.request.RetrieveChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.request.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.response.SendMessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UbuntuChatMessageService implements ChatMessageService {
    private final UbuntuChatRoomService ubuntuChatRoomService;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public SendMessageResponse saveMessage(SendMessageRequest sendMessageRequest) {
        RetrieveChatRoomRequest retrieveChatRoomRequest = new RetrieveChatRoomRequest();
        retrieveChatRoomRequest.setSender(sendMessageRequest.getSendId());
        retrieveChatRoomRequest.setRecipient(sendMessageRequest.getRecipientId());

        Optional<String> chatId = ubuntuChatRoomService.getAChatRoomId(retrieveChatRoomRequest);
        ChatMessage chatMessage = ChatMessage.builder()
                .chatMessageId(chatId.get())
                .sendId(sendMessageRequest.getSendId())
                .recipientId(sendMessageRequest.getRecipientId())
                .content(sendMessageRequest.getContent())
                .build();
        chatMessageRepository.save(chatMessage);

        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setSendId(chatMessage.getSendId());
        sendMessageResponse.setRecipientId(chatMessage.getRecipientId());
        sendMessageResponse.setMessage("Message Sent Successfully");
        return sendMessageResponse;

    }


    @Override
    public List<SendMessageResponse> findAllMessagesBtwSendAndRecipient(FindAllMessagesRequest findAllMessagesRequest) {
        RetrieveChatRoomRequest retrieveChatRoomRequest = new RetrieveChatRoomRequest();
        retrieveChatRoomRequest.setSender(findAllMessagesRequest.getSendId());
        retrieveChatRoomRequest.setRecipient(findAllMessagesRequest.getRecipientId());

        Optional<String> chatId = ubuntuChatRoomService.getAChatRoomId(retrieveChatRoomRequest);
        return chatMessageRepository.findByChatMessageId(chatId.get())
                .stream()
                .map(chatMessage -> new SendMessageResponse())
                .toList();

    }
}
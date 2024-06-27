package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.enums.MessageStatus;
import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import com.ubuntucontinues.ubuntu.data.models.ChatRoom;
import com.ubuntucontinues.ubuntu.data.repositories.ChatMessageRepository;
import com.ubuntucontinues.ubuntu.dto.requests.FindAllMessagesRequest;
import com.ubuntucontinues.ubuntu.dto.requests.RetrieveChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SendMessageRequest;
import com.ubuntucontinues.ubuntu.dto.responses.RecentChats;
import com.ubuntucontinues.ubuntu.dto.responses.SendMessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ubuntucontinues.ubuntu.data.enums.MessageStatus.READ;

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
        ChatMessage chatMessage = new ChatMessage(chatId.get(), sendMessageRequest.getSendId(),
                sendMessageRequest.getRecipientId(), sendMessageRequest.getContent());
        chatMessage.setStatus(MessageStatus.UNREAD);
        chatMessageRepository.save(chatMessage);
        SendMessageResponse sendMessageResponse = new SendMessageResponse();
        sendMessageResponse.setSendId(chatMessage.getSendId());
        sendMessageResponse.setRecipientId(chatMessage.getRecipientId());
        sendMessageResponse.setRoomId(chatId.get());
        sendMessageResponse.setMessage("Message Sent Successfully");
        return sendMessageResponse;

    }


    @Override
    public List<SendMessageResponse> findAllMessagesBtwSendAndRecipient(FindAllMessagesRequest findAllMessagesRequest) {
        RetrieveChatRoomRequest retrieveChatRoomRequest = new RetrieveChatRoomRequest();
        retrieveChatRoomRequest.setSender(findAllMessagesRequest.getSendId());
        retrieveChatRoomRequest.setRecipient(findAllMessagesRequest.getRecipientId());
        Optional<String> chatId = ubuntuChatRoomService.getAChatRoomId(retrieveChatRoomRequest);
        convertMessageToUnread(chatId.get(), findAllMessagesRequest.getSendId());
        return chatMessageRepository.findByChatMessageId(chatId.get())
                .stream()
                .map(SendMessageResponse::new)
                .toList();
    }

    @Override
    public List<RecentChats> findRecentlyChats(String senderEmail) {
        List<ChatRoom> chatRooms = ubuntuChatRoomService.findAllChatSenderChatRoom(senderEmail);

        if (!chatRooms.isEmpty()) {
            return getRecentChatEmails(senderEmail, chatRooms);
        }
        return new ArrayList<>();
    }

    private List<String> senderChatRooms(List<ChatRoom> chatRooms) {
        return chatRooms.stream()
                .map(ChatRoom::getChat_id)
                .toList();
    }

    private List<RecentChats> getRecentChatEmails(String senderEmail, List<ChatRoom> chatRooms) {
        List<String> chatIds = senderChatRooms(chatRooms);
        List<RecentChats> recentChats = new ArrayList<>();
        String[] splitChatId;
        int count = 0;
        for (String chatId : chatIds) {
            splitChatId = chatId.split("_");
            while (count != 2) {
                setRecentChats(senderEmail, splitChatId, count, recentChats);
                count++;
            }
            count = 0;
        }
        return recentChats;
    }

    private void setRecentChats(String senderEmail, String[] splitChatId, int count, List<RecentChats> recentChats) {
        if (!splitChatId[count].equals(senderEmail)) {
            RecentChats newRecentChat = new RecentChats();
            newRecentChat.setRecipientEmail(splitChatId[count]);
            newRecentChat.setNumberOfUnreadMessage(getUnReadMessage(senderEmail,splitChatId[count]));
            recentChats.add(newRecentChat);
        }
    }

    public Long getUnReadMessage(String sender, String recipient) {
        RetrieveChatRoomRequest retrieveChatRoomRequest = new RetrieveChatRoomRequest();
        retrieveChatRoomRequest.setSender(sender);
        retrieveChatRoomRequest.setRecipient(recipient);
        Optional<String> chatId = ubuntuChatRoomService.getAChatRoomId(retrieveChatRoomRequest);
        if (chatId.isPresent()) {
            return (long) chatMessageRepository.findByChatMessageId(chatId.get()).stream()
                    .filter(chatMessage -> isChatUnreadByTheRecipient(sender, chatMessage))
                    .toList()
                    .size();
        }
        return null;
    }


    private void convertMessageToUnread(String chatId, String senderId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatMessageId(chatId);
        chatMessages.stream()
                .filter(chatMessage -> chatMessage.getRecipientId().equals(senderId))
                .forEach(chatMessage -> setMessageToRead(chatMessage));

    }

    private void setMessageToRead(ChatMessage message) {
        message.setStatus(READ);
        chatMessageRepository.save(message);
    }

    private static boolean isChatUnreadByTheRecipient(String sender, ChatMessage chatMessage) {
        return chatMessage.getRecipientId().equals(sender) &&
                chatMessage.getStatus() == MessageStatus.UNREAD;
    }

}

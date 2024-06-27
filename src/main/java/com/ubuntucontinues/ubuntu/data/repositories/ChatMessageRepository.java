package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage,String > {

    List<ChatMessage> findByChatMessageId(String chatMessageId);
    List<ChatMessage> findByRecipientId(String recipientId);
}

package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findChatRoomBySenderEmailAndRecipientEmail(String sender, String recipient);

    List<ChatRoom> findChatRoomBySenderEmailOrRecipientEmail(String sender, String recipient);


}

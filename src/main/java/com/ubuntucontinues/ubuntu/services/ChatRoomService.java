package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.ChatRoom;
import com.ubuntucontinues.ubuntu.dto.requests.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.requests.RetrieveChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.responses.InitializeChatRoomResponse;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    InitializeChatRoomResponse initializeChatRoom(InitializeChatRoomRequest request);
    List<ChatRoom> findAll();
    CreateChatRoomResponse createChatRoom(String token);
    Optional<String> getAChatRoomId(RetrieveChatRoomRequest request);
}

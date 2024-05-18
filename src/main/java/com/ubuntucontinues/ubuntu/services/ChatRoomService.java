package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.ChatRoom;
import com.ubuntucontinues.ubuntu.dto.request.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.request.RetrieveChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.response.CreateChatRoomResponse;
import com.ubuntucontinues.ubuntu.dto.response.InitializeChatRoomResponse;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    InitializeChatRoomResponse initializeChatRoom(InitializeChatRoomRequest request);
    List<ChatRoom> findAll();
    CreateChatRoomResponse createChatRoom(String token);
    Optional<String> getAChatRoomId(RetrieveChatRoomRequest request);
}

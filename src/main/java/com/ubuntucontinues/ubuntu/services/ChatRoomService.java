package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.request.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.dto.response.InitializeChatRoomResponse;

public interface ChatRoomService {
    InitializeChatRoomResponse initializeChatRoom(InitializeChatRoomRequest request);

}

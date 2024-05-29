package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.InitializeChatRoomRequest;
import com.ubuntucontinues.ubuntu.services.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubuntu/chatroom")
@AllArgsConstructor
public class ChatRoomController {
    private ChatRoomService chatRoomService;

    @PostMapping("/initialize")
    public ResponseEntity<?> createChatRoom(@RequestBody InitializeChatRoomRequest request) {
        return ResponseEntity.ok(chatRoomService.initializeChatRoom(request));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateChatRoom(@RequestParam("token") String token){
        return ResponseEntity.ok(chatRoomService.activateChatRoom(token));
    }





}

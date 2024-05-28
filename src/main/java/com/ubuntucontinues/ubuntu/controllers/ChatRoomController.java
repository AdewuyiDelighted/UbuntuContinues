package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.services.ChatRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubuntu/chatroom")
@AllArgsConstructor
public class ChatRoomController {
    private ChatRoomService chatRoomService;

    @PostMapping("/createRoom")
    public ResponseEntity<?> createChatRoom(@RequestParam("token") String token) {
        return ResponseEntity.ok(chatRoomService.createChatRoom(token));
    }

}

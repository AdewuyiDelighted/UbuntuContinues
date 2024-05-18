package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.DisconnectUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DisconnectUserResponse;
import com.ubuntucontinues.ubuntu.dto.responses.SaveUserResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserWebsocketController {
    private UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public SaveUserResponse addUser(@Payload SaveUserRequest request) {
        return userService.saveUser(request);
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public DisconnectUserResponse disconnect(@Payload DisconnectUserRequest request) throws UserExistException {
        return userService.disconnect(request);
    }
}

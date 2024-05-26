package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.responses.FindAllUsersResponse;
import com.ubuntucontinues.ubuntu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserRestController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<FindAllUsersResponse> findConnectedUser(){
        return ResponseEntity.ok(userService.findConnectedUser());
    }
}

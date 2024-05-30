package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.LoginRequest;
import com.ubuntucontinues.ubuntu.dto.responses.FindAllUsersResponse;
import com.ubuntucontinues.ubuntu.dto.responses.LoginResponse;
import com.ubuntucontinues.ubuntu.exceptions.InvalidDetailException;
import com.ubuntucontinues.ubuntu.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ubuntu/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserRestController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<FindAllUsersResponse> findConnectedUser(){
        return ResponseEntity.ok(userService.findConnectedUser());
    }

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws InvalidDetailException {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping("/cohort/{cohortNumber}")
    public ResponseEntity<?> getAllMemberInACohort(@PathVariable String cohortNumber){
        return new ResponseEntity<>(userService.findAllMemberInACohort(cohortNumber), HttpStatus.OK);
    }

}

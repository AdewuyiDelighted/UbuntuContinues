package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.LoginRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAllUsersResponse;
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
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginRequest){
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, userService.login(loginRequest)));
        }catch(InvalidDetailException exception){
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/cohort/{cohortNumber}")
    public ResponseEntity<?> getAllMemberInACohort(@PathVariable String cohortNumber){
        return new ResponseEntity<>(userService.findAllMemberInACohort(cohortNumber), HttpStatus.OK);
    }

}

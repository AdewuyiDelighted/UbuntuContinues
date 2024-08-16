package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.exceptions.*;
import com.ubuntucontinues.ubuntu.services.CommunityManagerService;
import com.ubuntucontinues.ubuntu.services.EventService;
import com.ubuntucontinues.ubuntu.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/community_manager")
@AllArgsConstructor
@CrossOrigin("*")
public class CommunityManagerController {
    private final CommunityManagerService managerService;
    private final EventService eventServices;
    private final UserService userService;

    @PostMapping("/createEvent")
    public ResponseEntity<ApiResponse<?>> createEvent(@RequestPart(value = "image", required = false) MultipartFile file, @ModelAttribute CreateEventRequest createEventRequest) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventServices.createEvent(createEventRequest, file)), HttpStatus.OK);
        } catch (EventAlreadyExistException | IOException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/findEvent")
    public ResponseEntity<ApiResponse<?>> findEvent(@RequestParam("eventId") String eventId) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventServices.findEvent(eventId)), HttpStatus.FOUND);

        } catch (EventDoesntExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/findAllEvent")
    public ResponseEntity<ApiResponse<?>> findAllEvent() throws EventDoesntExistException {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventServices.findAllEvent()), HttpStatus.OK);
        } catch (EventDoesntExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/deleteEvent")
    public ResponseEntity<ApiResponse<?>> deleteEvent(@RequestParam("eventId") String eventId) throws EventDoesntExistException {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventServices.deleteEvent(eventId)), HttpStatus.OK);

        } catch (EventDoesntExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }

    }

    @PostMapping("/add_student")
    public ResponseEntity<ApiResponse<?>> addStudent(@RequestBody AddStudentRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, managerService.addStudent(request)), HttpStatus.ACCEPTED);
        } catch (UserExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/event")
    public ResponseEntity<ApiResponse<?>> updateEvent(@RequestBody UpdateEventRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, managerService.updateEvent(request)), HttpStatus.CREATED);
        } catch (EventExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }


    @DeleteMapping("/remove_student")
    public ResponseEntity<ApiResponse<?>> removeStudent(@RequestBody String userId) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, userService.dropDown(userId)), HttpStatus.OK);

        } catch (UserExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }


}

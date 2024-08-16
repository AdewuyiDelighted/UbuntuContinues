package com.ubuntucontinues.ubuntu.controllers;


import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ubuntu/chatroom")
@AllArgsConstructor
public class ChatEventController {
    private EventService eventService;

    @PostMapping("/createEvent")
    public ResponseEntity<ApiResponse<?>> createEvent(@RequestPart(value = "image", required = false) MultipartFile file, @ModelAttribute CreateEventRequest createEventRequest) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventService.createEvent(createEventRequest, file)), HttpStatus.OK);
        } catch (EventAlreadyExistException | IOException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/getAllEvents")
    public ResponseEntity<ApiResponse<?>> getAllEvents() {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventService.findAllEvent()), HttpStatus.OK);
        } catch (EventDoesntExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));

        }
    }


    @PostMapping("/getAEvent")
    public ResponseEntity<ApiResponse<?>> getAEvent(@RequestParam("eventId") String eventId) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventService.findEvent(eventId)), HttpStatus.OK);
        } catch (EventDoesntExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));

        }
    }

    @PostMapping("/deleteAEvent")
    public ResponseEntity<ApiResponse<?>> deleteAEvent(@RequestParam("eventId") String eventId) throws EventDoesntExistException {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventService.deleteEvent(eventId)), HttpStatus.OK);
        } catch (EventDoesntExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));

        }
    }

    @PostMapping("/updateEvent")
    public ResponseEntity<ApiResponse<?>> updateEvent(@RequestBody UpdateEventRequest updateEventRequest) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, eventService.updateEvent(updateEventRequest)), HttpStatus.OK);
        } catch (EventExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));

        }
    }


}

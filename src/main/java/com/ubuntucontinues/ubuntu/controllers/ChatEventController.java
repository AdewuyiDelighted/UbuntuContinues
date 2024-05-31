package com.ubuntucontinues.ubuntu.controllers;


import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
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
    public ResponseEntity<?> createEvent(@RequestPart(value = "image" ,required = false) MultipartFile file, @RequestPart CreateEventRequest createEventRequest) throws EventAlreadyExistException, IOException {
        return new ResponseEntity<>(eventService.createEvent(createEventRequest,file), HttpStatus.OK);
    }

    @PostMapping("/getAllEvents")
    public ResponseEntity<?> getAllEvents() throws EventDoesntExistException {
        return new ResponseEntity<>(eventService.findAllEvent(), HttpStatus.OK);
    }

    @PostMapping("/getAEvent")
    public ResponseEntity<?> getAEvent(@RequestParam("eventId") String eventId) throws EventDoesntExistException {
        return new ResponseEntity<>(eventService.findEvent(eventId), HttpStatus.OK);
    }

    @PostMapping("/deleteAEvent")
    public ResponseEntity<?> deleteAEvent(@RequestParam("eventId") String eventId) throws EventDoesntExistException {
        return new ResponseEntity<>(eventService.deleteEvent(eventId), HttpStatus.OK);
    }

    @PostMapping("/updateEvent")
    public ResponseEntity<?> updateEvent(@RequestBody UpdateEventRequest updateEventRequest) throws EventExistException {
        return new ResponseEntity<>(eventService.updateEvent(updateEventRequest), HttpStatus.OK);
    }


}

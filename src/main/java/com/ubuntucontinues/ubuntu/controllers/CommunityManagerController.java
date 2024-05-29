package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;
import com.ubuntucontinues.ubuntu.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/communityManger")
@AllArgsConstructor
@CrossOrigin("*")
public class CommunityManagerController {

    private final EventService eventServices;

    @PostMapping("/createEvent")
    public ResponseEntity<?> createEvent(CreateEventRequest createEventRequest) throws EventAlreadyExistException {
        return new ResponseEntity<>(eventServices.createEvent(createEventRequest), HttpStatus.CREATED);
    }

    @GetMapping("/findEvent")
    public ResponseEntity<?> findEvent(@RequestParam("eventId") String eventId) throws EventDoesntExistException {
        return new ResponseEntity<>(eventServices.findEvent(eventId), HttpStatus.FOUND);
    }

    @GetMapping("/findAllEvent")
    public ResponseEntity<?> findAllEvent() throws EventDoesntExistException {
        return new ResponseEntity<>(eventServices.findAllEvent(), HttpStatus.FOUND);
    }

    @PostMapping("/deleteEvent")
    public ResponseEntity<?> deleteEvent(@RequestParam("eventId") String eventId) throws EventDoesntExistException {
        return new ResponseEntity<>(eventServices.deleteEvent(eventId), HttpStatus.OK);
    }


}

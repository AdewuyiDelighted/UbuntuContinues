package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface  EventService {

   UpdateEventResponse updateEvent(UpdateEventRequest request) throws EventExistException;
   CreateEventResponse createEvent(CreateEventRequest createEventRequest, MultipartFile file) throws EventAlreadyExistException, IOException;

   FindAEventResponse findEvent(String eventId) throws EventDoesntExistException;

   List<FindAEventResponse> findAllEvent() throws EventDoesntExistException;
   DeleteEventResponse deleteEvent(String eventId) throws EventDoesntExistException;


}

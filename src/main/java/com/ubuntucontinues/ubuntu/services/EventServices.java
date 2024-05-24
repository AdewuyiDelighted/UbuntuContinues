package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;

import java.util.List;

public interface EventServices {

    CreateEventResponse createPost(CreateEventRequest createEventRequest) throws EventAlreadyExistException;

    FindAEventResponse findEvent(String eventId) throws EventDoesntExistException;

    List<FindAEventResponse> findAllEvent() throws EventDoesntExistException;
    DeleteEventResponse deleteEvent(String eventId) throws EventDoesntExistException;
}

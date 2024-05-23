package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.repositories.EventRepository;
import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventServices eventServices;
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void thatEventCanBeCreatedAndPosted() throws EventAlreadyExistException {

        CreateEventRequest createEventRequest = new CreateEventRequest();
        createEventRequest.setTitle("May Community Hangout 2024");
        createEventRequest.setDescription("A event to bring all the community members together to have fun ");
        createEventRequest.setEventDate(LocalDateTime.of(2024, 5, 24, 4, 0, 0));

        CreateEventResponse createEventResponse = eventServices.createPost(createEventRequest);
        assertThat(createEventResponse.getMessage(), is("Event Created Successfully"));
        assertEquals(1, eventRepository.count());

    }

    @Test
    public void testThatCreatedEventCanBeFind() throws EventDoesntExistException {
        String eventId = "664f416c68964e79e2eae90f";
        FindAEventResponse findAEventResponse = eventServices.findEvent(eventId);
        assertThat(findAEventResponse.getTitle(), is("May Community Hangout 2024"));
    }
    @Test public void testWeCanFindAllEventCreated() throws EventDoesntExistException {
        assertEquals(1,eventServices.findAllEvent().size());

    }

    @Test
    public void testThatEventCanBeRemovedByTheId() throws EventDoesntExistException {
        String eventId =  "664f4d6ea856782be9913e1e";
        DeleteEventResponse response = eventServices.deleteEvent(eventId);
        assertNotNull(response);
        assertThrows(EventDoesntExistException.class, ()-> eventServices.findEvent(eventId));
    }
}

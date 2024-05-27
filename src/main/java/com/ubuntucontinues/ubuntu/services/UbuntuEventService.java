package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Event;
import com.ubuntucontinues.ubuntu.data.repositories.EventRepository;
import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;
import com.ubuntucontinues.ubuntu.util.AppUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
@AllArgsConstructor
public class UbuntuEventService implements EventServices {
    private EventRepository eventRepository;
    private ModelMapper modelMapper;

    @Override
    public CreateEventResponse createEvent(CreateEventRequest createEventRequest) throws EventAlreadyExistException {
        Optional<Event> foundEvent = eventExist(createEventRequest.getTitle());

        if (foundEvent.isEmpty()) {
            Event event = Event.builder()
                    .title(createEventRequest.getTitle())
                    .description(createEventRequest.getDescription())
                    .eventDate(createEventRequest.getEventDate())
                    .eventImage(createEventRequest.getEventImage())
                    .dateCreated(LocalDateTime.now())
                    .build();
            eventRepository.save(event);

            CreateEventResponse createEventResponse = modelMapper.map(event, CreateEventResponse.class);
            createEventResponse.setMessage(NEW_EVENT_MESSAGE);
            return createEventResponse;
        }
        throw new EventAlreadyExistException(EVENT_ALREADY_EXIST);
    }

    @Override
    public FindAEventResponse findEvent(String eventId) throws EventDoesntExistException {
        Event foundEvent = eventRepository.findById(eventId).orElseThrow(() -> new EventDoesntExistException(EVENT_DOESNT_EXIST));
        return modelMapper.map(foundEvent, FindAEventResponse.class);
    }

    @Override
    public List<FindAEventResponse> findAllEvent() throws EventDoesntExistException {
        List<Event> events = eventRepository.findAll();
        if (!events.isEmpty()) {
            List<FindAEventResponse> findAEventResponses = eventRepository.findAll(

                    )
                    .stream()
                    .map(event -> modelMapper.map(event, FindAEventResponse.class))
                    .toList();
            System.out.println(findAEventResponses);
            return findAEventResponses;
        }
        throw new EventDoesntExistException(NO_EVENT_AVAILABLE);
    }

    @Override
    public DeleteEventResponse deleteEvent(String eventId) throws EventDoesntExistException {
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new EventDoesntExistException(EVENT_DOESNT_EXIST));
        eventRepository.delete(event);
        DeleteEventResponse eventResponse = new DeleteEventResponse();
        eventResponse.setDeleteEventId(event.getId());
        eventResponse.setMessage(DELETE_EVENT_MESSAGE);
        return eventResponse;
    }

    private Optional<Event> eventExist(String title) {
        return eventRepository.findByTitle(title);
    }


}


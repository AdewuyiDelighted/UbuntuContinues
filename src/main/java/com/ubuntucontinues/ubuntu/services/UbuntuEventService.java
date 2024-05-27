package com.ubuntucontinues.ubuntu.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.ubuntucontinues.ubuntu.data.models.Event;
import com.ubuntucontinues.ubuntu.data.repositories.EventRepository;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.ubuntucontinues.ubuntu.data.models.Event;
import com.ubuntucontinues.ubuntu.data.repositories.EventRepository;
import com.ubuntucontinues.ubuntu.dto.requests.CreateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventDoesntExistException;
import com.ubuntucontinues.ubuntu.util.AppUtils;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
@Slf4j
@Service
@AllArgsConstructor
public class UbuntuEventService implements EventService{
    private EventRepository eventRepository;
    private ModelMapper modelMapper;
    @Override
    public UpdateEventResponse updateEvent(UpdateEventRequest request) throws EventExistException {
        Event event = findEventById(request.getEventId());
        List<JsonPatchOperation> jsonPatchOperations = new ArrayList<>();
        buildPatchOperations(request,jsonPatchOperations);
        event = applyPatch(jsonPatchOperations,event);
        event = eventRepository.save(event);
        UpdateEventResponse response = builedUpdateEventResponse();
            response.setEventId(event.getId());

        return response;
    }

    private UpdateEventResponse builedUpdateEventResponse() {
        UpdateEventResponse updateEventResponse = new UpdateEventResponse();
        updateEventResponse.setMessage("Event updated successfully");
        return updateEventResponse;
    }

    private Event applyPatch(List<JsonPatchOperation> jsonPatchOperations, Event event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonPatch jsonPatch = new JsonPatch(jsonPatchOperations);
            log.info("patch operations: {}", jsonPatch);
            JsonNode eventNode = objectMapper.convertValue(event,JsonNode.class);
            JsonNode updatedEventNode = jsonPatch.apply(eventNode);
            event = objectMapper.convertValue(updatedEventNode,Event.class);

         } catch (Exception e) {
            throw new RuntimeException(e);
        }
    return event;

    }

    private void buildPatchOperations(UpdateEventRequest request,List<JsonPatchOperation> jsonPatchOperations){
            Class<?> updateRequestClass = request.getClass();
        Field[] fields = updateRequestClass.getDeclaredFields();
            stream(fields)
                    .filter(field -> isValidUpdate(field,request))
                    .forEach(field ->   addOperation(request,field,jsonPatchOperations));

    }


    private void addOperation(UpdateEventRequest request, Field field, List<JsonPatchOperation> jsonPatchOperations) {
                try{
                    JsonPointer path = new JsonPointer("/"+field.getName());
                    log.info("Initial Event JSON: {}", field.get(request));
                    JsonNode value = new TextNode(field.get(request).toString());
                    ReplaceOperation replaceOperation = new ReplaceOperation(path,value);
                    jsonPatchOperations.add(replaceOperation);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
    }

    private boolean isValidUpdate(Field field, UpdateEventRequest request) {
        field.setAccessible(true);
    try {
        return field.get(request) != null &&
                !(field.getName().equalsIgnoreCase("eventId"));
     } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
    }

    }

    private Event findEventById(String eventId) throws EventExistException {

    return eventRepository.findById(eventId)
            .orElseThrow(()-> new EventExistException(
                    String.format("Sorry!!! event with this %s does not exist ",eventId)));
    }
    @Override
    public CreateEventResponse createPost(CreateEventRequest createEventRequest) throws EventAlreadyExistException {
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
            List<FindAEventResponse> findAEventResponses = eventRepository.findAll()
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

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
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
@Slf4j
@Service
@AllArgsConstructor
public class UbuntuEventService implements EventService{
    private EventRepository eventRepository;
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
}

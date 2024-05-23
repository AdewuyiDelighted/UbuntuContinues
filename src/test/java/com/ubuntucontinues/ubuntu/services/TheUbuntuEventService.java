package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Event;
import com.ubuntucontinues.ubuntu.data.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TheUbuntuEventService {
   private EventRepository repository;
   public Event saveEntity (){
       Event event = new Event();
       event.setId("12");
       event.setEventDate(LocalDateTime.parse("2024-05-23T15:30:45"));
       event.setDescription("A hangout party");
       event.setTitle("Hangout");
       repository.save(event);
       return event;
    }
}

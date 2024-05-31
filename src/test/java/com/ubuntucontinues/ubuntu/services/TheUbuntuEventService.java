package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Event;
import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.EventRepository;
import com.ubuntucontinues.ubuntu.data.repositories.QuestionRepository;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TheUbuntuEventService {
   private EventRepository repository;
   private QuestionRepository questionRepository;
   private UserService userService;
   public Event saveEntity (){
       Event event = Event.builder().build();
       event.setId("12");
//       event.setEventDate(LocalDateTime.parse("2024-05-23T15:30:45"));
       event.setDescription("A hangout party");
       event.setTitle("Hangout");
       repository.save(event);
       return event;
    }
public Question saveQuestion() throws UserExistException {
       Question question = new Question();
       question.setBody("Please tell me more about java");
       User user = userService.findBy("664f5e40def86e197934f07d");
       question.setUser(user);
       question.setTitle("What is java");
       questionRepository.save(question);
       return question;
}

}

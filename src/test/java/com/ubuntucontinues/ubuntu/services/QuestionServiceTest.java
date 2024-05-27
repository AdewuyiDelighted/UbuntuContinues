package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.QuestionRepository;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.UploadQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.QuestionResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UploadQuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private QuestionRepository repository;

    @Test
    public void testTHatQuestionWithOutAValidUserThrowsException(){
        UploadQuestionRequest uploadQuestionRequest = new UploadQuestionRequest();
        uploadQuestionRequest.setUserId("wrong_username");
        assertThrows(UserExistException.class, () -> questionService.postQuestion(uploadQuestionRequest));
    }

    @Test
    public void testThatQuestionCanBeUploadedWhichIsAddedToRepo() throws UserExistException {
        String userId = "1234";
        User user = new User("1234", "test", "testing", "testing@gmail.com", "password");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        UploadQuestionRequest request = new UploadQuestionRequest();
        request.setUserId(userId);
        request.setBody("I am tired with junit testing");
        request.setTitle("Junit Testing");
        request.setTags(List.of("Junit", "Java", "Testing"));
        UploadQuestionResponse response = questionService.postQuestion(request);
        when(repository.findAll()).thenReturn(List.of(new Question()));
        assertNotNull(response);
        assertThat(questionService.findAll().size()).isEqualTo(1);
    }

    @Test
    public void testThatQuestionWhenUploadedCanFindByTheId() throws QuestionDoesNotExistException {
        String questionId = "123456";
        Question question = new Question();
        question.setTitle("test");
        question.setBody("test i a m ");
        question.setId(questionId);
        when(repository.findById(questionId)).thenReturn(Optional.of(question));
        QuestionResponse response = questionService.findAQuestion(questionId);
        assertNotNull(response);
        assertThat(response.getId()).isEqualTo(questionId);
    }

    @Test public void testThatUserCanGetAllQuestionBelongingToThem() throws UserExistException {
        String userId = "123456";
        User user = new User(userId, "test", "testing", "test@email.com", "password");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(repository.findAllByUser(user)).
                thenReturn(List.of(new Question(), new Question(), new Question()));
        assertThat(questionService.findAllByUser(userId).size()).isEqualTo(3);
    }



}
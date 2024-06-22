package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.data.repositories.QuestionRepository;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.UploadQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteQuestionResponse;
import com.ubuntucontinues.ubuntu.dto.responses.QuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testTHatQuestionWithOutAValidUserThrowsException() {
        UploadQuestionRequest uploadQuestionRequest = new UploadQuestionRequest();
        uploadQuestionRequest.setUserId("wrong_username");
        assertThrows(UserExistException.class, () -> questionService.postQuestion(uploadQuestionRequest));
    }

//    @Test
//    public void testThatQuestionCanBeUploadedWhichIsAddedToRepo() throws UserExistException {
//        String userId = "1234";
//        User user = new User("1234", "test", "testing", "testing@gmail.com");
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        UploadQuestionRequest request = new UploadQuestionRequest();
//        request.setUserId(userId);
//        request.setBody("I am tired with junit testing");
//        request.setTitle("Junit Testing");
//        request.setTags(List.of("Junit", "Java", "Testing"));
//        UploadQuestionResponse response = questionService.postQuestion(request);
//        when(repository.findAll()).thenReturn(List.of(new Question()));
//        assertNotNull(response);
//        assertThat(questionService.findAll().size()).isEqualTo(1);
//    }

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

    @Test
    public void testThatUserCanDeleteAQuestionBelongingToThem() {
        String questionId = "123456";
        Question question = new Question();
        question.setTitle("test");
        question.setBody("test i a m ");
        question.setId(questionId);
        when(repository.findById(questionId)).thenReturn(Optional.of(question));
        DeleteQuestionResponse deleteQuestionResponse = questionService.deleteAQuestion(questionId);
        assertThat(deleteQuestionResponse.getMessage(), is("Question Deleted Successfully"));
    }

    @Test
    public void testThatUserCanStreamThroughTheQuestionAndGetTheQuestionSimilarByTitle(){
        Question question = new Question();
        question.setTitle("For loops keep iterating");
        Question question1 = new Question();
        question1.setTitle("Loops");
        when(repository.findAll()).thenReturn(List.of(question, question1));
        assertThat(questionService.findSimilarTitleQuestion("loops").size()).isEqualTo(2);
    }


}
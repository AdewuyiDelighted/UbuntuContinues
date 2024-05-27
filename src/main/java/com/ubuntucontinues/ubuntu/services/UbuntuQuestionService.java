package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.QuestionRepository;
import com.ubuntucontinues.ubuntu.dto.requests.UploadQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteQuestionResponse;
import com.ubuntucontinues.ubuntu.dto.responses.QuestionResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UploadQuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ubuntucontinues.ubuntu.util.AppUtils.QUESTION_NOT_EXIST;
import static com.ubuntucontinues.ubuntu.util.AppUtils.QUESTION_UPLOADED_MESSAGE;

@Service
@Slf4j
public class UbuntuQuestionService implements QuestionService {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public UploadQuestionResponse postQuestion(UploadQuestionRequest uploadQuestionRequest) throws UserExistException {
        User user = userService.findBY(uploadQuestionRequest.getUserId());
        Question newQuestion = new Question();
        newQuestion.setUser(user);
        newQuestion.setBody(uploadQuestionRequest.getBody());
        newQuestion.setTitle(uploadQuestionRequest.getTitle());
        newQuestion.setTags(uploadQuestionRequest.getTags());
        Question savedQuestion = questionRepository.save(newQuestion);
        UploadQuestionResponse response = mapper.map(newQuestion, UploadQuestionResponse.class);
        response.setMessage(QUESTION_UPLOADED_MESSAGE);
        response.setQuestionId(newQuestion.getId());
        log.info("Question uploaded -> {}", response);
        log.info("Question uploaded -> {}", savedQuestion);
        return response;
    }

    @Override
    public List<QuestionResponse> findAll() {
        return questionRepository.findAll()
                .stream()
                .map(question -> mapper.map(question, QuestionResponse.class))
                .toList();
    }

    @Override
    public QuestionResponse findAQuestion(String questionId) throws QuestionDoesNotExistException {
        return mapper.map(questionRepository.findById(questionId)
                        .orElseThrow(() -> new QuestionDoesNotExistException(QUESTION_NOT_EXIST)),
                QuestionResponse.class);
    }

    @Override
    public List<QuestionResponse> findAllByUser(String userId) throws UserExistException {
        return questionRepository.findAllByUser(userService.findBY(userId))
                .stream()
                .map(question -> mapper.map(question, QuestionResponse.class))
                .toList();
    }

    @Override
    public DeleteQuestionResponse deleteAQuestion(String questionId) {
        Optional<Question> foundQuestion = questionRepository.findById(questionId);
        foundQuestion.ifPresent(question -> questionRepository.delete(question));
        DeleteQuestionResponse response = new DeleteQuestionResponse();
        response.setMessage("Question Deleted Successfully");
        return response;
    }
}

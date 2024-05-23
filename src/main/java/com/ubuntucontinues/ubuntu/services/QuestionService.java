package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UploadQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.QuestionResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UploadQuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

import java.util.List;

public interface QuestionService {

    UploadQuestionResponse postQuestion(UploadQuestionRequest uploadQuestionRequest) throws UserExistException;
    List<QuestionResponse> findAll();
    QuestionResponse findAQuestion(String questionId) throws QuestionDoesNotExistException;
}

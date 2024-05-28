package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;
import com.ubuntucontinues.ubuntu.dto.requests.UploadQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DeleteQuestionResponse;
import com.ubuntucontinues.ubuntu.dto.responses.QuestionResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UploadQuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

import java.util.Collection;
import java.util.List;
public interface QuestionService {
    Question findBy(String questionId) throws QuestionExistException;
    UploadQuestionResponse postQuestion(UploadQuestionRequest uploadQuestionRequest) throws UserExistException;
    List<QuestionResponse> findAll();
    QuestionResponse findAQuestion(String questionId) throws QuestionDoesNotExistException;
    List<QuestionResponse> findAllByUser(String userId) throws UserExistException;

    DeleteQuestionResponse deleteAQuestion(String questionId);
}

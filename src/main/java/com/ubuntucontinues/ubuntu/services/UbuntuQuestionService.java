package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.data.repositories.QuestionRepository;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UbuntuQuestionService implements QuestionService{
    private QuestionRepository questionRepository;
    @Override
    public Question findBy(String questionId) throws QuestionExistException {
        return questionRepository.findById(questionId)
                .orElseThrow(()->new QuestionExistException("\"err\" :\"Not a valid question\""));
    }
}

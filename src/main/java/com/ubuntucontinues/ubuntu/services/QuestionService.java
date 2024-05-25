package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;

public interface QuestionService {
    Question findBy(String questionId) throws QuestionExistException;
}

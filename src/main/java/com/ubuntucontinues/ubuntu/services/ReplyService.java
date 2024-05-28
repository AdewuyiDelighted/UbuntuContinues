package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UbuntuReplyToQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UbuntuReplyToQuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

public interface ReplyService {
    UbuntuReplyToQuestionResponse reply(UbuntuReplyToQuestionRequest request) throws UserExistException, QuestionExistException;
}

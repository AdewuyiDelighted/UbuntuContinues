package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.data.models.Reply;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.ReplyRepository;
import com.ubuntucontinues.ubuntu.dto.requests.UbuntuReplyToQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UbuntuReplyToQuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UbuntuReplyService implements ReplyService {
    private ReplyRepository replyRepository;
    private UserService userService;
    private QuestionService questionService;
    private ModelMapper modelMapper;

    @Override
    public UbuntuReplyToQuestionResponse reply(UbuntuReplyToQuestionRequest request) throws UserExistException, QuestionExistException {
        log.info("User id -> {}", request.getUserReplyingId());
        log.info("User id -> {}", request.getQuestionId());

        User user = findUserBy(request.getUserReplyingId());
        Question question = findQuestionById(request.getQuestionId());
        Reply reply = modelMapper.map(request, Reply.class);
        replyRepository.save(reply);
        UbuntuReplyToQuestionResponse response = new UbuntuReplyToQuestionResponse();
        response.setMessage("Replied successfully");
        return response;
    }

    @Override
    public List<UbuntuReplyToQuestionResponse> questionReplies(String questionId) throws QuestionExistException {
        Question question = questionService.findBy(questionId);
        return replyRepository.findRepliesByReplyTo(question)
                .stream().map(reply -> modelMapper.map(reply, UbuntuReplyToQuestionResponse.class))
                .toList();
    }


    private Question findQuestionById(String questionId) throws QuestionExistException {
        return questionService.findBy(questionId);
    }

    private User findUserBy(String userReplyingId) throws UserExistException {
        return userService.findBy(userReplyingId);
    }
}

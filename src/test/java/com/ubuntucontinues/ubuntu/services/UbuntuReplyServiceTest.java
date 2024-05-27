package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.dto.requests.UbuntuReplyToQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UbuntuReplyToQuestionResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UbuntuReplyServiceTest {
    @Autowired
    private ReplyService replyService ;
    @Autowired
    private TheUbuntuEventService eventService;
    @Test
    public void testThatUserCanAnswerQuestion() throws QuestionExistException, UserExistException {
        UbuntuReplyToQuestionRequest request = new UbuntuReplyToQuestionRequest();
        Question question = eventService.saveQuestion();
        request.setQuestionId(question.getId());
        request.setBody("This is the answer to the question");
        request.setUserReplyingId("664f5e40def86e197934f07d");
        request.setQuestionId("664f632452e41c100fa2fb77");
        UbuntuReplyToQuestionResponse response = replyService.reply(request);
        assertNotNull(response);
        assertEquals("This is the answer to the question",response.getMessage());



    }



}

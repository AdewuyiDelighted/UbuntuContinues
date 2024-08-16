package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.UploadQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionDoesNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubuntu/question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/postQuestion")
    public ResponseEntity<?> postQuestion(@RequestBody UploadQuestionRequest uploadQuestionRequest) throws UserExistException {
        return new ResponseEntity<>(questionService.postQuestion(uploadQuestionRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getUserQuestion")
    public ResponseEntity<?> getUserQuestion(@RequestParam("userId") String userId) throws UserExistException {
        return new ResponseEntity<>(questionService.findAllByUser(userId), HttpStatus.OK);
    }


    @GetMapping("/getQuestion")
    public ResponseEntity<?> getAllQuestion() {
        return new ResponseEntity<>(questionService.findAllQuestions(), HttpStatus.OK);
    }

    @PostMapping("/getAQuestion")
    public ResponseEntity<ApiResponse<?>> getAQuestion(@RequestParam("questionId") String questionId) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, questionService.findAQuestion(questionId)), HttpStatus.OK);
        } catch (QuestionDoesNotExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @PostMapping("/deleteQuestion")
    public ResponseEntity<?> deleteQuestion(@RequestParam("questionId") String questionId) {
        return new ResponseEntity<>(questionService.deleteAQuestion(questionId), HttpStatus.OK);
    }

}

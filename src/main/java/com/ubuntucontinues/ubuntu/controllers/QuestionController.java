package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.UploadQuestionRequest;
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

//    @GetMapping("/getQuestions")
//    public ResponseEntity<?> getAllQuestions() {
//        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
//    }
    @GetMapping("/getQuestion")
    public ResponseEntity<?> getAllQuestion() {
        return new ResponseEntity<>(questionService.findAllQuestions(), HttpStatus.OK);
    }

    @PostMapping("/getAQuestion")
    public ResponseEntity<?> getAQuestion(@RequestParam("questionId") String questionId) throws QuestionDoesNotExistException {
        return new ResponseEntity<>(questionService.findAQuestion(questionId), HttpStatus.OK);
    }
    @PostMapping("/deleteQuestion")
    public ResponseEntity<?> deleteQuestion(@RequestParam ("questionId") String questionId){
        return new ResponseEntity<>(questionService.deleteAQuestion(questionId),HttpStatus.OK);
    }

}

package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.UbuntuReplyToQuestionRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.ReplyService;
//import com.ubuntucontinues.ubuntu.util.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reply")
@AllArgsConstructor
public class ReplyController {
    private ReplyService replyService;

    @PostMapping("/reply")
    public ResponseEntity<ApiResponse<?>> reply(@RequestBody UbuntuReplyToQuestionRequest request)  {
        try{
            return new ResponseEntity<>(new ApiResponse<>(true,replyService.reply(request)), HttpStatus.OK);
        } catch (QuestionExistException | UserExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/reply")
    public ResponseEntity<ApiResponse<?>> getQuestionReplies(@RequestParam("questionId") String questionId)  {
       try{
           return new  ResponseEntity<>(new ApiResponse<>(true,replyService.questionReplies(questionId)),HttpStatus.OK);
       } catch (QuestionExistException exception) {
           return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
       }
    }

//    private ApiResponse getApiResponseResponseEntity(BindingResult result) {
//        if (result.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
//            for (FieldError error : result.getFieldErrors()) {
//                errorMessage.append("Field '")
//                        .append(error.getField())
//                        .append("' ")
//                        .append(error.getDefaultMessage())
//                        .append("; ");
//            }
//            return GenerateApiResponse.validationError(errorMessage.toString());
//        }
//        return null;
//    }

}

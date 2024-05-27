package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.UbuntuReplyToQuestionRequest;
import com.ubuntucontinues.ubuntu.exceptions.QuestionExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.services.ReplyService;
import com.ubuntucontinues.ubuntu.util.ApiResponse;
import com.ubuntucontinues.ubuntu.util.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reply")
@AllArgsConstructor
public class ReplyController {
    private ReplyService replyService;

    @PostMapping("/reply")
    public ResponseEntity<?> reply (@RequestBody UbuntuReplyToQuestionRequest request) throws QuestionExistException, UserExistException {
       return ResponseEntity.ok(replyService.reply(request));
    }

    private ApiResponse getApiResponseResponseEntity(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
            for (FieldError error : result.getFieldErrors()) {
                errorMessage.append("Field '")
                        .append(error.getField())
                        .append("' ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            return GenerateApiResponse.validationError(errorMessage.toString());
        }
        return null;
    }

}

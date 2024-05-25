package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import com.ubuntucontinues.ubuntu.services.CommunityManagerService;
import com.ubuntucontinues.ubuntu.util.ApiResponse;
import com.ubuntucontinues.ubuntu.util.GenerateApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/community_manager/")
@AllArgsConstructor
public class CommunityManagerController {
   private final CommunityManagerService communityManagerService;
    @PostMapping("saveUser")
    public ResponseEntity<?> addStudent(@Valid @RequestBody AddStudentRequest request , BindingResult result){
        ApiResponse errorMessage = getApiResponseResponseEntity(result);
        if(errorMessage!=null) return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(communityManagerService.addStudent(request));


    }

    @PatchMapping("update_event")
    public ResponseEntity<?> updateEvent( @Valid @RequestBody  UpdateEventRequest request, BindingResult result) throws EventExistException {

        ApiResponse errorMessage = getApiResponseResponseEntity(result);
        if(errorMessage!=null) return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(communityManagerService.updateEvent(request));
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

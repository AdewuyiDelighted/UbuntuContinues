package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.CreateCohortRequest;
import com.ubuntucontinues.ubuntu.dto.requests.FindCohortRequest;
import com.ubuntucontinues.ubuntu.dto.responses.ApiResponse;
import com.ubuntucontinues.ubuntu.exceptions.CohortAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.CohortNotExistException;
import com.ubuntucontinues.ubuntu.exceptions.EventAlreadyExistException;
import com.ubuntucontinues.ubuntu.services.CohortService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/ubuntu/cohort")
@CrossOrigin("*")
@AllArgsConstructor
public class CohortController {
    private final CohortService cohortService;

    @PostMapping("/createCohort")
    public ResponseEntity<ApiResponse<?>> createCohort(@RequestBody CreateCohortRequest createCohortRequest) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, cohortService.createCohort(createCohortRequest)), HttpStatus.OK);
        } catch (CohortAlreadyExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }


    @GetMapping("/findCohort")
    public ResponseEntity<ApiResponse<?>> findCohort(@RequestBody FindCohortRequest findCohortRequest) {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, cohortService.findCohort(findCohortRequest)), HttpStatus.OK);
        } catch (CohortAlreadyExistException | CohortNotExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

    @GetMapping("/findAllCohort")
    public ResponseEntity<ApiResponse<?>> findCohort() {
        try {
            return new ResponseEntity<>(new ApiResponse<>(true, cohortService.findAllCohort()), HttpStatus.OK);

        } catch (CohortNotExistException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage()));
        }
    }

}

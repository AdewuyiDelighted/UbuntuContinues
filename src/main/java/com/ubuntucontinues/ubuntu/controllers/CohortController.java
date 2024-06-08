package com.ubuntucontinues.ubuntu.controllers;

import com.ubuntucontinues.ubuntu.dto.requests.CreateCohortRequest;
import com.ubuntucontinues.ubuntu.dto.requests.FindCohortRequest;
import com.ubuntucontinues.ubuntu.exceptions.CohortAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.CohortNotExistException;
import com.ubuntucontinues.ubuntu.services.CohortService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubuntu/cohort")
@CrossOrigin("*")
@AllArgsConstructor
public class CohortController {
    private final CohortService cohortService;

    @PostMapping("/createCohort")
    public ResponseEntity<?> createCohort(@RequestBody CreateCohortRequest createCohortRequest) throws CohortAlreadyExistException {
        return ResponseEntity.status(200).body(cohortService.createCohort(createCohortRequest));
    }

    @GetMapping("/findCohort")
    public ResponseEntity<?> findCohort(@RequestBody FindCohortRequest findCohortRequest) throws CohortAlreadyExistException, CohortNotExistException {
        return new ResponseEntity<>(cohortService.findCohort(findCohortRequest), HttpStatus.FOUND);
    }

    @GetMapping("/findAllCohort")
    public ResponseEntity<?> findCohort() throws CohortNotExistException {
        return new ResponseEntity<>(cohortService.findAllCohort(), HttpStatus.OK);
    }

}

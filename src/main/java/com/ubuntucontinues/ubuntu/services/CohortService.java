package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.CreateCohortRequest;
import com.ubuntucontinues.ubuntu.dto.requests.FindCohortRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateCohortResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindCohortResponse;
import com.ubuntucontinues.ubuntu.exceptions.CohortAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.CohortNotExistException;

import java.util.List;

public interface CohortService {
    CreateCohortResponse createCohort(CreateCohortRequest createCohortRequest) throws CohortAlreadyExistException;


    FindCohortResponse findCohort(FindCohortRequest findCohortRequest) throws CohortNotExistException, CohortAlreadyExistException;

    List<FindCohortResponse> findAllCohort() throws CohortNotExistException;
}
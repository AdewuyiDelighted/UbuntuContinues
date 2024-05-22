package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.repositories.CohortRepository;
import com.ubuntucontinues.ubuntu.dto.requests.CreateCohortRequest;
import com.ubuntucontinues.ubuntu.dto.requests.FindCohortRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateCohortResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindCohortResponse;
import com.ubuntucontinues.ubuntu.exceptions.CohortAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.CohortNotExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CohortServiceTest {
    @Autowired
    private CohortService cohortService;
    @Autowired
    private CohortRepository cohortRepository;


    @Test
    public void testThatCohortCanBeCreated() throws CohortAlreadyExistException, CohortNotExistException {

        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setCohortNumber("1");
        createCohortRequest.setCohortName("Alpha");
        CreateCohortResponse createCohortResponse = cohortService.createCohort(createCohortRequest);
        assertThat(createCohortResponse.getMessage(), is("Cohort Registered Successfully"));

        FindCohortRequest findCohortRequest = new FindCohortRequest();
        findCohortRequest.setCohortNumber("1");
        findCohortRequest.setCohortName("Alpha");
        FindCohortResponse findCohortResponse = cohortService.findCohort(findCohortRequest);

        assertThat(findCohortResponse.getCohortNumber(), is(createCohortRequest.getCohortNumber()));
        assertEquals(1, cohortRepository.count());

    }
}

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Cohort;
import com.ubuntucontinues.ubuntu.data.repositories.CohortRepository;
import com.ubuntucontinues.ubuntu.dto.requests.CreateCohortRequest;
import com.ubuntucontinues.ubuntu.dto.requests.FindCohortRequest;
import com.ubuntucontinues.ubuntu.dto.responses.CreateCohortResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindCohortResponse;
import com.ubuntucontinues.ubuntu.exceptions.CohortAlreadyExistException;
import com.ubuntucontinues.ubuntu.exceptions.CohortNotExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ubuntucontinues.ubuntu.util.AppUtils.COHORT_NOT_AVAILABLE;
import static com.ubuntucontinues.ubuntu.util.AppUtils.NEW_COHORT_MESSAGE;

@Service
@AllArgsConstructor
public class UbuntuCohortService implements CohortService {
    private CohortRepository cohortRepository;
    private ModelMapper modelMapper;

    @Override
    public CreateCohortResponse createCohort(CreateCohortRequest createCohortRequest) throws CohortAlreadyExistException {
        Optional<Cohort> foundCohort = cohortRepository.findCohortByCohortNumberAndCohortName(createCohortRequest.getCohortNumber(), createCohortRequest.getCohortName());

        if (foundCohort.isEmpty()) {
            Cohort cohort = Cohort.builder()
                    .cohortName(createCohortRequest.getCohortName())
                    .cohortNumber(createCohortRequest.getCohortName())
                    .build();

            cohort.setCohortName(createCohortRequest.getCohortName());
            cohort.setCohortNumber(createCohortRequest.getCohortNumber());
            cohortRepository.save(cohort);

            CreateCohortResponse createCohortResponse = new CreateCohortResponse();
            createCohortResponse.setMessage(NEW_COHORT_MESSAGE);
            return createCohortResponse;

        }
        throw new CohortAlreadyExistException(COHORT_NOT_AVAILABLE);
    }

    @Override
    public FindCohortResponse findCohort(FindCohortRequest findCohortRequest) throws CohortAlreadyExistException {
        Cohort foundCohort = cohortExist(findCohortRequest.getCohortNumber(),findCohortRequest.getCohortName());
        if (foundCohort != null) {
            return modelMapper.map(foundCohort, FindCohortResponse.class);
        }
        throw new CohortAlreadyExistException(COHORT_NOT_AVAILABLE);
    }

    private Cohort cohortExist(String cohortNumber,String cohortName) {
        Optional<Cohort> foundCohort = cohortRepository.findCohortByCohortNumberAndCohortName(cohortNumber,cohortName);
        System.out.println(foundCohort.get());
        return foundCohort.orElse(null);
    }


}

package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.Cohort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CohortRepository  extends MongoRepository<Cohort,String> {

    Optional<Cohort> findCohortByCohortNumberAndCohortName(String cohortNumber,String cohortName);
    Optional<Cohort> findCohortByCohortNumber(String cohortNumber);
}

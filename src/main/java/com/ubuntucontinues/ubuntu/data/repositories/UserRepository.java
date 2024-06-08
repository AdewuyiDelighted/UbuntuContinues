package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.enums.Status;
import com.ubuntucontinues.ubuntu.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);

    Optional<User> findByEmail(String email);
    Optional<User> findUserByEmail(String email);
    List<User> findUsersByCohort_CohortNumber(String cohortNumber);
}

package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventRepository extends MongoRepository<Event,String> {

    Optional<Event> findByTitle(String title);

}

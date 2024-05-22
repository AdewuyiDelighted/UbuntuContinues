package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}

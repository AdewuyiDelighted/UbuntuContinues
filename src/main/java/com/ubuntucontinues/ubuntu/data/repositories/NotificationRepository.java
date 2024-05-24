package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}

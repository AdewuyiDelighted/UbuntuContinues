package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.UbuntuNotification;
import com.ubuntucontinues.ubuntu.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<UbuntuNotification,String> {
    List<UbuntuNotification> findAllByUser(User user);
}

package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository  extends MongoRepository<Image,String> {
}

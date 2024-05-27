package com.ubuntucontinues.ubuntu.data.repositories;

import com.ubuntucontinues.ubuntu.data.models.Question;
import com.ubuntucontinues.ubuntu.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface QuestionRepository extends MongoRepository<Question,String> {
    List<Question> findAllByUser(User user);
}

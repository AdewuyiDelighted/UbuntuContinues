package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.SaveUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UbuntuUserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testThatCanSaveUser(){
        SaveUserRequest request = new SaveUserRequest();
        User user = new User();
        user.setFullName("Abigail Godwin");
        user.setUserName("Abby");
        request.setUser(user);
        SaveUserResponse response = userService.saveUser(request);
        response.setUser(user);
        assertNotNull(response);

    }

@Test
    public void test(){

}

}

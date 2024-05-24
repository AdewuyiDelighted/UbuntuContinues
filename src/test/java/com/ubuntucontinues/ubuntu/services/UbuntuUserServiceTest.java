package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DropDownResponse;
import com.ubuntucontinues.ubuntu.dto.responses.SaveUserResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UbuntuUserServiceTest {
    @MockBean
    private UserRepository userRepository;
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
    public void testDropDownStudent() throws UserExistException {
        String dropDownUserId = "664e2b2ff1228add5bc592df";
        User user = new User(dropDownUserId, "test", "testing", "test@email");
        when(userRepository.findById(dropDownUserId)).thenReturn(Optional.of(user));

        DropDownResponse response = userService.dropDown(dropDownUserId);

        when(userRepository.findById(dropDownUserId)).thenReturn(Optional.empty());

        assertNotNull(response);

        assertThrows(UserExistException.class, () -> userService.findUser(dropDownUserId));
    }


@Test
    public void test(){

}

}

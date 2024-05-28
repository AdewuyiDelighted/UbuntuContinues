package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.AddUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.LoginRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddUserResponse;
import com.ubuntucontinues.ubuntu.dto.responses.DropDownResponse;
import com.ubuntucontinues.ubuntu.dto.responses.LoginResponse;
import com.ubuntucontinues.ubuntu.dto.responses.SaveUserResponse;
import com.ubuntucontinues.ubuntu.exceptions.InvalidDetailException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UbuntuUserServiceTest {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void testThatCanSaveUser() {
        SaveUserRequest request = new SaveUserRequest();
        User user = new User();
        user.setFullName("Abigail Godwin");
        user.setUserName("Abby");
        user.setCohortNumber(2L);
        user.setEmail("babyUye@gmail.com");
        request.setUser(user);
        SaveUserResponse response = userService.saveUser(request);
        response.setUser(user);
        assertNotNull(response);
    }


    @Test
    public void testDropDownStudent() throws UserExistException {
        String dropDownUserId = "664e2b2ff1228add5bc592df";
        User user = new User(dropDownUserId, "test", "testing", "test@email", "password");
        when(userRepository.findById(dropDownUserId)).thenReturn(Optional.of(user));

        DropDownResponse response = userService.dropDown(dropDownUserId);

        when(userRepository.findById(dropDownUserId)).thenReturn(Optional.empty());

        assertNotNull(response);

        assertThrows(UserExistException.class, () -> userService.findUser(dropDownUserId));
    }

    @Test
    public void testThatUserCanNotLoginWithAUsernameWhichDoesNotThrowException() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("wrongemail");
        loginRequest.setPassword("password");
        assertThrows(InvalidDetailException.class, () -> userService.login(loginRequest));
    }

    @Test
    public void testThatWhenUserHasAnAccountAndTriesLoginWithWrongPasswordThrowsException() {
        String userEmail = "test@email";
        when(userRepository.findUserByEmail(userEmail))
                .thenReturn(Optional.of(new User("1234",
                        "test",
                        "testing",
                        "test@email.com",
                        "password")));
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(userEmail);
        loginRequest.setPassword("wrongPassword");
        assertThrows(InvalidDetailException.class, () -> userService.login(loginRequest));
    }

    @Test
    public void testThatWhenUserLoginToApplicationReturnsATokenAndAMessage() throws InvalidDetailException {
        String userEmail = "test@email";
        when(userRepository.findUserByEmail(userEmail))
                .thenReturn(Optional.of(new User("1234",
                        "test",
                        "testing",
                        "test@email.com",
                        "password")));
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(userEmail);
        loginRequest.setPassword("password");
        LoginResponse response = userService.login(loginRequest);
        assertNotNull(response);
        assertNotNull(response.getMessage());
        assertNotNull(response.getToken());
    }


    @Test
    public void test() {

    }

}

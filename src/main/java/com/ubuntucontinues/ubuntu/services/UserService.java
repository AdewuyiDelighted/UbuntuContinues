package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.DisconnectUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.LoginRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.*;
import com.ubuntucontinues.ubuntu.exceptions.InvalidDetailException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

import java.util.List;

public interface UserService {
    SaveUserResponse saveUser(SaveUserRequest request);
    DisconnectUserResponse disconnect(DisconnectUserRequest request) throws UserExistException;
    FindAllUsersResponse findConnectedUser();
    DropDownResponse dropDown(String userId) throws UserExistException;
    UserResponse findUser(String dropDownUserId) throws UserExistException;
    User findBY(String userName) throws UserExistException;
    LoginResponse login(LoginRequest loginRequest) throws InvalidDetailException;
    List<User> getAllUsers();

    List<User> getAllUnActivated();

    User findBy(String userId) throws UserExistException;

    void setLoginPassword(User user,String password) throws UserExistException;

    void saveAll(List<User> members);

    void checkUserExistByEmail(String email) throws UserExistException;
}

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.DisconnectUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.*;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

public interface UserService {
    SaveUserResponse saveUser(SaveUserRequest request);
    DisconnectUserResponse disconnect(DisconnectUserRequest request) throws UserExistException;
    FindAllUsersResponse findConnectedUser();
    DropDownResponse dropDown(String userId) throws UserExistException;
    UserResponse findUser(String dropDownUserId) throws UserExistException;
    User findBY(String userName) throws UserExistException;
}

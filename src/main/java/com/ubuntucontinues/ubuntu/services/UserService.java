package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.DisconnectUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DisconnectUserResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAllUsersResponse;
import com.ubuntucontinues.ubuntu.dto.responses.SaveUserResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;

public interface UserService {
     SaveUserResponse saveUser(SaveUserRequest request);
     DisconnectUserResponse disconnect(DisconnectUserRequest request) throws UserExistException;
     FindAllUsersResponse findConnectedUser();
}

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.enums.Status;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.DisconnectUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DisconnectUserResponse;
import com.ubuntucontinues.ubuntu.dto.responses.FindAllUsersResponse;
import com.ubuntucontinues.ubuntu.dto.responses.SaveUserResponse;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class
UbuntuUserService implements UserService{
    private final UserRepository userRepository;
    public SaveUserResponse saveUser(SaveUserRequest saveUserRequest){
        saveUserRequest.getUser().setStatus(Status.ONLINE);
        userRepository.save(saveUserRequest.getUser());
        SaveUserResponse response = new SaveUserResponse();
        response.setUser(saveUserRequest.getUser());
        return response;
    }

    public DisconnectUserResponse disconnect(DisconnectUserRequest request) throws UserExistException {
        User foundUser = findBy(request.getUserName());
        foundUser.setStatus(Status.OFFLINE);
        userRepository.save(foundUser);
        DisconnectUserResponse response = new DisconnectUserResponse();
        response.setUser(foundUser);
        return response;


    }

    public FindAllUsersResponse findConnectedUser(){
        FindAllUsersResponse response = new FindAllUsersResponse();
        List<User> users = userRepository.findAllByStatus(Status.ONLINE);
        response.setUsers(users);
        return response;
    }

    private User findBy(String userName) throws UserExistException {
        return userRepository.findById(userName)
                .orElseThrow(()->new UserExistException("\"err\" :\"Not a valid user\""));
    }



}

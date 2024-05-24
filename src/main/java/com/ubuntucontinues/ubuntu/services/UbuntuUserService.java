package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.enums.Status;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.DisconnectUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.*;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.DROP_DOWN_MESSAGE;

@Service
@RequiredArgsConstructor
public class
UbuntuUserService implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
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

    @Override
    public DropDownResponse dropDown(String userId) throws UserExistException {
        User user = findBy(userId);
        userRepository.delete(user);
        DropDownResponse response = new DropDownResponse();
        response.setMessage(String.format(DROP_DOWN_MESSAGE, userId));
        return response;
    }

    @Override
    public UserResponse findUser(String dropDownUserId) throws UserExistException {
        User user = findBy(dropDownUserId);
        return modelMapper.map(user, UserResponse.class);
    }

    private User findBy(String userName) throws UserExistException {
        return userRepository.findById(userName)
                .orElseThrow(()->new UserExistException("\"err\" :\"Not a valid user\""));
    }
    @Override
    public User findBY(String userName) throws UserExistException {
        return userRepository.findById(userName)
                .orElseThrow(()->new UserExistException("\"err\" :\"Not a valid user\""));
    }





}

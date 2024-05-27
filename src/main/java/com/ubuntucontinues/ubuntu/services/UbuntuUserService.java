package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.enums.Status;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.data.repositories.UserRepository;
import com.ubuntucontinues.ubuntu.dto.requests.DisconnectUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.LoginRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.responses.*;
import com.ubuntucontinues.ubuntu.exceptions.InvalidDetailException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
@RequiredArgsConstructor
public class UbuntuUserService implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    public SaveUserResponse saveUser(SaveUserRequest saveUserRequest){
        saveUserRequest.getUser().setStatus(Status.ONLINE);
        userRepository.save(saveUserRequest.getUser());
        SaveUserResponse response = new SaveUserResponse();
        response.setUser(saveUserRequest.getUser());
        return response;
    }

    public DisconnectUserResponse disconnect(DisconnectUserRequest request) throws UserExistException {
        User foundUser = findBy(request.getUserId());
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


    public User findBy(String id) throws UserExistException {
        return userRepository.findById(id)
                .orElseThrow(()->new UserExistException("\"err\" :\"Not a valid user\""));
    }
    @Override
    public User findBY(String userName) throws UserExistException {
        return userRepository.findById(userName)
                .orElseThrow(()->new UserExistException("\"err\" :\"Not a valid user\""));
    }
    public Optional<User> findByEmail(String email) throws UserExistException {
        return Optional.ofNullable(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserExistException("\"err\" :\"Not a valid user\"")));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws InvalidDetailException  {
        User user = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new InvalidDetailException(INVALID_DETAIL));
        if (!user.getPassword().equals(loginRequest.getPassword())) throw new InvalidDetailException(INVALID_DETAIL);
        String token = jwtService.createToken(user.getId(), user.getEmail());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("Login successfully");
        return response;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


}

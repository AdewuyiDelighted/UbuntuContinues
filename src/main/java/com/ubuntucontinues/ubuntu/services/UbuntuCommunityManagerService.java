package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UbuntuCommunityManagerService implements CommunityManagerService{
    private ModelMapper modelMapper;
    private  UserService userService;
    EventService eventService;
    @Override
    public AddStudentResponse addStudent(AddStudentRequest request) {
        User user = modelMapper.map(request,User.class);
        SaveUserRequest request1 = new SaveUserRequest();
        request1.setUser(user);
        userService.saveUser(request1);
       AddStudentResponse response = new AddStudentResponse();
        response.setUser(user);
        return response ;
    }

    @Override
    public UpdateEventResponse updateEvent(UpdateEventRequest request) throws EventExistException {

        return eventService.updateEvent(request);
    }


}

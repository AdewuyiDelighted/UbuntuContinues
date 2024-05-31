package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Cohort;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.SaveUserRequest;
import com.ubuntucontinues.ubuntu.dto.requests.StudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.util.AppUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UbuntuCommunityManagerService implements CommunityManagerService{
    private ModelMapper modelMapper;
    private  UserService userService;
    private EventService eventService;
    private CohortService cohortService;

    @Override
    public AddStudentResponse addStudent(AddStudentRequest request) throws UserExistException {
        Cohort cohort = cohortService.findCohortBCohortNumber(request.getCohortNumber());
        List<User> members = new ArrayList<>();
        for (StudentRequest studentRequest : request.getMembers()) {
            userService.checkUserExistByEmail(studentRequest.getEmail());
            User user = new User();
            user.setEmail(studentRequest.getEmail());
            user.setFullName(studentRequest.getFullName());
            user.setCohort(cohort);
            members.add(user);
            userService.saveAll(members);
        }
        AddStudentResponse response = new AddStudentResponse();
        response.setMessage(AppUtils.MEMBERS_ADDED_SUCCESSFULLY);
        return response;

    }
//    public AddStudentResponse addStudent(AddStudentRequest request) {
//        Cohort cohort = cohortService.findCohortBCohortNumber(request.getCohortNumber());
//        User user = modelMapper.map(request,User.class);
//        user.setCohort(cohort);
//        SaveUserRequest request1 = new SaveUserRequest();
//        request1.setUser(user);
//        userService.saveUser(request1);
//       AddStudentResponse response = new AddStudentResponse();
//        response.setMessage("Member added successfully");
//        return response ;
//    }

    @Override
    public UpdateEventResponse updateEvent(UpdateEventRequest request) throws EventExistException {
        return eventService.updateEvent(request);
    }


}

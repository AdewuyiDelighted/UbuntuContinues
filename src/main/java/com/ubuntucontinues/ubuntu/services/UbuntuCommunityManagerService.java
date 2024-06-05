package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.data.models.Cohort;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import com.ubuntucontinues.ubuntu.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UbuntuCommunityManagerService implements CommunityManagerService{
    private  UserService userService;
    private EventService eventService;
    private CohortService cohortService;
    private ScheduleSendNotificationService sendNotificationService;

    @Override
    public AddStudentResponse addStudent(AddStudentRequest request) {
        Cohort cohort = cohortService.findCohortByCohortNumber(request.getCohortNumber());
        List<User> members = new ArrayList<>();
        for (String email : request.getMembers().keySet()) {
            try {
                userService.checkUserExistByEmail(email);
            }catch (UserExistException exception){
                continue;
            }
            User user = mapUser(email, request.getMembers().get(email), cohort);
            members.add(user);
            userService.saveAll(members);
        }
        sendNotificationService.sendLoginEmail(members);
        AddStudentResponse response = new AddStudentResponse();
        response.setMessage(AppUtils.MEMBERS_ADDED_SUCCESSFULLY);
        return response;
    }

    private static User mapUser(String email, String fullName, Cohort cohort) {
        User user = new User();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setCohort(cohort);
        return user;
    }


    @Override
    public UpdateEventResponse updateEvent(UpdateEventRequest request) throws EventExistException {
        return eventService.updateEvent(request);
    }


}

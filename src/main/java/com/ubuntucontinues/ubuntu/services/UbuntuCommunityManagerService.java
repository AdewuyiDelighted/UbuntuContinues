package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.config.BeanConfig;
import com.ubuntucontinues.ubuntu.data.models.Cohort;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.*;

@Service
@AllArgsConstructor
public class UbuntuCommunityManagerService implements CommunityManagerService{
    private  UserService userService;
    private EventService eventService;
    private CohortService cohortService;
    private ScheduleSendNotificationService sendNotificationService;
    private EmailService emailService;
    private BeanConfig beanConfig;

    @Override
    public AddStudentResponse addStudent(AddStudentRequest request) {
        Cohort cohort = cohortService.findCohortByCohortNumber(request.getCohortNumber());
        List<User> members = new ArrayList<>();
        List<String> unRegisteredEmail = new ArrayList<>();
        for (String email : request.getMembers().keySet()) {
            if (checkUserByEmail(email, unRegisteredEmail)) continue;
            User user = mapUser(email, request.getMembers().get(email), cohort);
            members.add(user);
            userService.saveAll(members);
        }
        sendNotificationService.sendLoginEmail(members);
        sendMessageToCommunityManager(request, unRegisteredEmail);
        AddStudentResponse response = new AddStudentResponse();
        response.setMessage(MEMBERS_ADDED_SUCCESSFULLY);
        return response;
    }

    private boolean checkUserByEmail(String email, List<String> unRegisteredEmail) {
        try {
            userService.checkUserExistByEmail(email);
        }catch (UserExistException exception){
            unRegisteredEmail.add(email);
            return true;
        }
        return false;
    }

    private void sendMessageToCommunityManager(AddStudentRequest request, List<String> unRegisteredEmail) {
        if (!unRegisteredEmail.isEmpty()) {
            emailService.sendMessage(new Sender(APP_EMAIL, EMAIL_NAME),
                    ADD_STUDENT_MESSAGE(String.join(",", unRegisteredEmail), request.getMembers().size(), unRegisteredEmail.size()),
                    List.of(new Recipient(beanConfig.communityManagerEmail, COMMUNITY_MANAGER)),
                    ADD_STUDENT);
        }
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

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.DropDownResponse;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import com.ubuntucontinues.ubuntu.exceptions.UserExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UbuntuCommunityManagerServiceTest {
    @Autowired
    private CommunityManagerService communityManagerService;
    private UserService userService;


//    @Test
//    public void testThatCommunityManagerCanAddStudent() throws UserExistException {
//
//        AddStudentRequest request = new AddStudentRequest();
//        request.setEmail("regiusportus@gmail.com");
//        request.setFullName("Abigail Godwin");
//        request.setCohortNumber("1");
//        AddStudentResponse response = communityManagerService.addStudent(request);
//        assertNotNull(response);
//
//
//    }
//    @Test
//    public void testThatCommunityManagerCanAddStudent(){
//        AddStudentRequest request = new AddStudentRequest();
//        request.setEmail("regiusportus@gmail.com");
//        request.setFullName("Abigail Godwin");
//        request.setCohortNumber(1L);
//        AddStudentResponse response = communityManagerService.addStudent(request);
//        assertNotNull(response);
//
//
//    }

    @Test
    public void testThatCommunityManagerCanUpdateEvent() throws EventExistException {


        UpdateEventRequest request = new UpdateEventRequest();
        request.setTitle("Community hangout");
        request.setDescription("Time to have fun and make friends");
        request.setEventId("12");
        UpdateEventResponse response = communityManagerService.updateEvent(request);
        assertNotNull(response);
        assertEquals("Community hangout","Community hangout");



    }
    @Test
    public void testThatCommunityManagerCanRemoveStudent() throws UserExistException {
        int allUserCount = userService.getAllUsersCount();
        String userId ="665f079ab92577073796b41e";
    DropDownResponse response =  userService.dropDown(userId);
        assertEquals(allUserCount-1,userService.getAllUsersCount());
    }

}

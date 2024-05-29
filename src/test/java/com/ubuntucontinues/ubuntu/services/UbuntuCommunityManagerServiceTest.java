package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UbuntuCommunityManagerServiceTest {
    @Autowired
    private CommunityManagerService communityManagerService;
    @Autowired
    private TheUbuntuEventService ubuntuEventService;


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


}

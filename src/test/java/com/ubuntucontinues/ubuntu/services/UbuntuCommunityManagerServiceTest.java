package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.response.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UbuntuCommunityManagerServiceTest {
    @Autowired
    private CommunityManagerService communityManagerService;

    @Test
    public void testThatCommunityManagerCanAddStudent(){
        AddStudentRequest request = new AddStudentRequest();
        request.setEmail("regiusportus@gmail.com");
        request.setFullName("Abigail Godwin");
        request.setCohortNumber(1L);
        AddStudentResponse response = communityManagerService.addStudent(request);
        assertNotNull(response);


    }

    @Test
    public void testThatCommunityManagerCanUpdateEvent(){
        UpdateEventRequest request = new UpdateEventRequest();
        request.setTitle("Community hangout");
        request.setDescription("Time to have fun and make friends");
        UpdateEventResponse response = communityManagerService.updateEvent(request);
        assertNotNull(response);
        assertEquals("Community hangout",response.getEvent().getTitle());


    }


}

package com.ubuntucontinues.ubuntu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.StudentRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import com.ubuntucontinues.ubuntu.services.CommunityManagerService;
import com.ubuntucontinues.ubuntu.util.AppUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommunityManagerController.class)
public class UbuntuCommunityManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityManagerService communityManagerService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void testAddStudentSuccess() throws Exception {
        AddStudentRequest request = new AddStudentRequest();
        request.setCohortNumber(2L);
        StudentRequest request1 = new StudentRequest();
        request1.setEmail("Ola@gmail.com");
        request1.setFullName("Ola aina");
        request.setMembers(List.of(request1));
        User user = new User();
        user.setEmail(request.getMembers().getFirst().getEmail());
        user.setFullName(request.getMembers().getFirst().getFullName());
        AddStudentResponse response = new AddStudentResponse();
        response.setMessage(AppUtils.MEMBERS_ADDED_SUCCESSFULLY);

        when(communityManagerService.addStudent(any(AddStudentRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/community_manager/saveUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("Norah Joy"))
                .andExpect(jsonPath("$.email").value("norah1@gmail.com"));
    }

}

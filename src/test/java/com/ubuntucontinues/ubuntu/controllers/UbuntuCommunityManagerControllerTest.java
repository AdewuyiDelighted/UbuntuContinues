package com.ubuntucontinues.ubuntu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubuntucontinues.ubuntu.data.models.User;
import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import com.ubuntucontinues.ubuntu.services.CommunityManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


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
        request.setFullName("Norah Joy");
        request.setEmail("norah1@gmail.com");
        User user = new User();
        user.setEmail(request.getEmail());
        user.setCohortNumber(request.getCohortNumber());
        user.setFullName(request.getFullName());
        AddStudentResponse response = new AddStudentResponse();
        response.setUser(user);

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

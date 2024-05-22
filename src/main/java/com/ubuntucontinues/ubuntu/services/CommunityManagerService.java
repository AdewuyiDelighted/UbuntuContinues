package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.response.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;

public interface CommunityManagerService {

    AddStudentResponse addStudent(AddStudentRequest request);

    UpdateEventResponse updateEvent(UpdateEventRequest request);
}

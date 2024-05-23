package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.AddStudentRequest;
import com.ubuntucontinues.ubuntu.dto.requests.UpdateEventRequest;
import com.ubuntucontinues.ubuntu.dto.responses.UpdateEventResponse;
import com.ubuntucontinues.ubuntu.dto.responses.AddStudentResponse;
import com.ubuntucontinues.ubuntu.exceptions.EventExistException;

public interface CommunityManagerService {

    AddStudentResponse addStudent(AddStudentRequest request);

    UpdateEventResponse updateEvent(UpdateEventRequest request) throws EventExistException;
}

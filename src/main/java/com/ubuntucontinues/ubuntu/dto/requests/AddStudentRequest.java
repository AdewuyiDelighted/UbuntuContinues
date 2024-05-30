package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddStudentRequest {
    private List<StudentRequest> members;
    private Long cohortNumber;
}


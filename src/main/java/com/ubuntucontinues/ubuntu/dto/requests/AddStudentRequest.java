package com.ubuntucontinues.ubuntu.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddStudentRequest {
    private String cohortNumber;
    private List<StudentRequest> members;

}


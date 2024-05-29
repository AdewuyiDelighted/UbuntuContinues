package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddStudentRequest {
    private String fullName;
    @NotBlank(message = "Required field")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,}$", message = "Please enter a valid email address.")
    private String email;
    private String cohortNumber;
    private List<StudentRequest> members;

}


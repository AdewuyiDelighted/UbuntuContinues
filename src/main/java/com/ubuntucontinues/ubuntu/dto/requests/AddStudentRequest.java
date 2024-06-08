package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class AddStudentRequest {
    private String cohortNumber;
    private Map<String, String> members;
}


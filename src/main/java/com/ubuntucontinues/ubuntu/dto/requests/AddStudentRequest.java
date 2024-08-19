package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Map;

@Setter
@Getter
@ToString
public class AddStudentRequest {
    private String cohortNumber;
    private Map<String, String> members;
}


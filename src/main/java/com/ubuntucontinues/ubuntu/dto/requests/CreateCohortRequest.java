package com.ubuntucontinues.ubuntu.dto.requests;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCohortRequest {
    private String cohortNumber;
    private String cohortName;
}

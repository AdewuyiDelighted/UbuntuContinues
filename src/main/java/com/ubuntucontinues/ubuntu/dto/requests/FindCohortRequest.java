package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindCohortRequest {
    private String cohortNumber;
    private String cohortName;
}

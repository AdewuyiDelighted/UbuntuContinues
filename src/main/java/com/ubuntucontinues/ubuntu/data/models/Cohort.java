package com.ubuntucontinues.ubuntu.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
public class Cohort {
    @Id
    private String cohortNumber;
    private String cohortName;
}

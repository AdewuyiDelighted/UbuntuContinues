package com.ubuntucontinues.ubuntu.data.models;

import com.ubuntucontinues.ubuntu.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {
    @Id
    private String id;
    private String userName;
    private String fullName;
    private Status status;
    @DBRef
    private Cohort cohort;
    private String email;
    private String profilePicture;
}
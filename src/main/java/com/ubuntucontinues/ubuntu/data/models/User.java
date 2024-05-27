package com.ubuntucontinues.ubuntu.data.models;

import com.ubuntucontinues.ubuntu.data.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String userName;
    private String fullName;
    private Status status = Status.OFFLINE;
    @DBRef
    private Cohort cohort;
    private String email;
    @DBRef
    private Image profilePicture;
    private String password;

    public User(String id, String userName, String fullName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
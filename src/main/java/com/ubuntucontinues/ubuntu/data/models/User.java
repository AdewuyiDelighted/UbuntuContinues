package com.ubuntucontinues.ubuntu.data.models;

import com.ubuntucontinues.ubuntu.data.enums.AccountState;
import com.ubuntucontinues.ubuntu.data.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;

@Getter
@Setter
@Document
@NoArgsConstructor
@ToString
public class User {
    @Id
    private String id;
    private String userName;
    private String fullName;
    private Status status = Status.OFFLINE;
    private String email;
    private Long cohortNumber;
    private String password;
    private AccountState accountState=AccountState.NOT_ACTIVATED;
    @DBRef
    private Cohort cohort;
    @DBRef
    private Image profilePicture;

    public User(String id, String userName, String fullName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
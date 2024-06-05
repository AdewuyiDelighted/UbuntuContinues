package com.ubuntucontinues.ubuntu.data.models;

import com.ubuntucontinues.ubuntu.data.enums.AccountState;
import com.ubuntucontinues.ubuntu.data.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.ubuntucontinues.ubuntu.data.enums.AccountState.NOT_ACTIVATED;

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
    private String password;
    private AccountState accountState = NOT_ACTIVATED;
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


    public User(String userName, String fullName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
}
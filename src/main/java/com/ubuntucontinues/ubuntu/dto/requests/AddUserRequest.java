package com.ubuntucontinues.ubuntu.dto.requests;

import com.ubuntucontinues.ubuntu.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AddUserRequest {
    private String userName;
    private String fullName;
    private Status status = Status.OFFLINE;
    private String email;
    private Long cohortNumber;
    private String password;
}

package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.enums.AccountState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String email;
    private AccountState accountState;
}

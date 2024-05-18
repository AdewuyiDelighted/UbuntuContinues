package com.ubuntucontinues.ubuntu.dto.requests;

import com.ubuntucontinues.ubuntu.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserRequest {
    private User user;
}

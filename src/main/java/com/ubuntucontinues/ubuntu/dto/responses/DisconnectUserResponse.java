package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisconnectUserResponse {
    private User user;
}

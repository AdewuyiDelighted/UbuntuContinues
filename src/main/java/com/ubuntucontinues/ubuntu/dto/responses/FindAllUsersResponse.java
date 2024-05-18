package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class FindAllUsersResponse {
    private List<User> users;
}

package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddStudentResponse {
    private User user;
}

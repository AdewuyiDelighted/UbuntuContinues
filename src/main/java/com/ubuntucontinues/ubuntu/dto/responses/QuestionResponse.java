package com.ubuntucontinues.ubuntu.dto.responses;

import com.ubuntucontinues.ubuntu.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponse {
    private String id;
    private String title;
    private String body;
    private String codeSnippet;
    private User user;
}

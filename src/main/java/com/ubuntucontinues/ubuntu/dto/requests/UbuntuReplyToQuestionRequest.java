package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UbuntuReplyToQuestionRequest {
    private String body;
    private String userReplyingId;
    private String questionId;


}

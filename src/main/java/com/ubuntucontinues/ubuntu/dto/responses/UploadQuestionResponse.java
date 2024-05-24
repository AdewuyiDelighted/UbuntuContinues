package com.ubuntucontinues.ubuntu.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UploadQuestionResponse {
    private String message;
    private String questionId;
    private String title;
    private String body;
    private List<String> tags;
}

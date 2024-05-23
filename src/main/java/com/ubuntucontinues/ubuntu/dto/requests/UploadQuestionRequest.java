package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UploadQuestionRequest {
    private String title;
    private String body;
    private String userId;
    private List<String> tags;
}

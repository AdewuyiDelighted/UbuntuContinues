package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePostRequest {
    private String postId;
    private String title;
    private String body;
}

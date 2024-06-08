package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikePostRequest {
    private String userId;
    private String postId;
}

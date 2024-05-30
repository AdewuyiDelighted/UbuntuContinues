package com.ubuntucontinues.ubuntu.dto.requests;

import com.ubuntucontinues.ubuntu.data.models.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetAllPostResponse {
    private List<Post> posts;
}

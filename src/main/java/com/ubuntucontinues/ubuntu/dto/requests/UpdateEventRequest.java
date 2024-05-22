package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateEventRequest {
    private String title;
    private String description;
}

package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class CreateEventRequest {
    private String id;
    private String title;
    private String description;
    private List<String> eventImage;
    private LocalDateTime eventDate;

}

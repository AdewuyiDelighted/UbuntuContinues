package com.ubuntucontinues.ubuntu.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class FindAEventResponse {
    private String title;
    private String description;
    private List<String> eventImage;
    private LocalDateTime eventDate;
    private LocalDateTime dateCreated;
}

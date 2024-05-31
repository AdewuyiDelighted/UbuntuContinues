package com.ubuntucontinues.ubuntu.dto.requests;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class CreateEventRequest {
    private String id;
    private String title;
    private String description;
//    private List<String> eventImage
    private String eventDate;

}

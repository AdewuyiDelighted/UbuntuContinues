package com.ubuntucontinues.ubuntu.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Document
@Builder
public class Event {
    @Id
    private String id;
    private String title;
    private String description;
    private List<String> eventImage;
    private LocalDateTime eventDate;
    private LocalDateTime dateCreated;
}

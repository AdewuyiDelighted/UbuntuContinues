package com.ubuntucontinues.ubuntu.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@Document
@Builder
public class Event {
    private String name;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private LocalDateTime dateCreated;
}

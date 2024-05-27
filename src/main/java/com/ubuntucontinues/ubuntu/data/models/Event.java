package com.ubuntucontinues.ubuntu.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document
@Builder
public class Event {
    private String id;
    private String title;
    private String description;
    @JsonIgnore
    private LocalDateTime dateCreated = LocalDateTime.now();
   @JsonIgnore
   private LocalDateTime eventDate;
    private List<String> eventImage;
   }


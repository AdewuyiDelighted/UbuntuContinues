package com.ubuntucontinues.ubuntu.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class Event {
   private  String id;
   private String title;
   private String description;
   private LocalDateTime dateCreated;
   private LocalDateTime eventDate;

}



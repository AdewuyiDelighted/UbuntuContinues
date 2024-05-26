package com.ubuntucontinues.ubuntu.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@Builder
public class Image {
    @Id
    private String imageId;
    private User user;
    private String url;
}

package com.ubuntucontinues.ubuntu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipient {
    private String email;
    private String name;

    public Recipient(String recipient, String recipient1) {
        this.email = recipient;
        this.name = recipient1;
    }
}

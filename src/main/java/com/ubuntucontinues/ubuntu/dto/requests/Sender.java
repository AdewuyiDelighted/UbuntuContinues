package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sender {
    private String email;
    private String name;

    public Sender(String sender, String sender1) {
        this.email = sender;
        this.name = sender1;
    }
}

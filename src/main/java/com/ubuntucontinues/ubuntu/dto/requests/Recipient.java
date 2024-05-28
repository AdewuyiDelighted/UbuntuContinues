package com.ubuntucontinues.ubuntu.dto.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Recipient {
    private String email;
    private String fullName;
    private String password;


    public Recipient(String recipient, String recipient1) {
        this.email = recipient;
        this.fullName = recipient1;
    }
}

package com.ubuntucontinues.ubuntu.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class BrevoMailRequest {
    private Sender sender;
    private String subject;
    private String htmlContent;
    private List<Recipient> to;
}

package com.ubuntucontinues.ubuntu.dto.requests;

import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;
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

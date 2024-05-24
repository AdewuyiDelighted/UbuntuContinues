package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;

import java.util.List;

public interface EmailService {

    void sendMessage(Sender sender, String message, List<Recipient> recipient, String subject);





}

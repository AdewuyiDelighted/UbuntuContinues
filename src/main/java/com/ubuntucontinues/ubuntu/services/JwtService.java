package com.ubuntucontinues.ubuntu.services;

public interface JwtService {
    String createToken(String senderEmail, String recipientEmail);
}

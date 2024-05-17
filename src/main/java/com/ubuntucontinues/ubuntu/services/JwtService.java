package com.ubuntucontinues.ubuntu.services;

public interface JwtService {
    String createToken(String senderEmail, String recipientEmail);
    <T>T decodeToken(String token, Class<T> clazz);

}

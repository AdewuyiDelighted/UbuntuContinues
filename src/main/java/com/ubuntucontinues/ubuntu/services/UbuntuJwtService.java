package com.ubuntucontinues.ubuntu.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UbuntuJwtService implements JwtService{

    @Value("${oauth.api.key}")
    private String secretKey;

    @Override
    public String createToken(String senderEmail, String recipientEmail) {
        return JWT.create()
                .withIssuer("quiz-application")
                .withSubject("access_token")
                .withClaim("sender_email", senderEmail)
                .withClaim("recipient_email", recipientEmail)
                .withExpiresAt(Instant.now().plusSeconds(20*86400))
                .sign(Algorithm.HMAC256(secretKey));
    }
}

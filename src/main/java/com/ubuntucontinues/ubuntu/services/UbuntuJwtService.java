package com.ubuntucontinues.ubuntu.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Map;

@Service
public class UbuntuJwtService implements JwtService {

    @Value("${oauth.api.key}")
    private String secretKey;


    @Override
    public String createToken(String senderEmail, String recipientEmail) {
        return JWT.create()
                .withIssuer("quiz-application")
                .withSubject("access_token")
                .withClaim("sender_email", senderEmail)
                .withClaim("recipient_email", recipientEmail)
                .withExpiresAt(Instant.now().plusSeconds(20 * 86400))
                .sign(Algorithm.HMAC256(secretKey));
    }

    @Override
    public <T> T decodeToken(String token, Class<T> clazz) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        T decodedObject;
        try {
            decodedObject = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to create an instance of the target class", e);
        }
        extractFields(clazz, claims, decodedObject);
        return decodedObject;
    }

    private static <T> void extractFields(Class<T> clazz, Map<String, Claim> claims, T decodedObject) {
        for (Map.Entry<String, Claim> entry : claims.entrySet()) {
            String claim = entry.getKey();
            Claim value = entry.getValue();
            try {
                Field field = clazz.getDeclaredField(claim);
                field.setAccessible(true);
                field.set(decodedObject, value.asString());
            } catch (Exception ignored) {
            }
        }
    }
}

package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.request.BrevoMailRequest;
import com.ubuntucontinues.ubuntu.dto.request.Recipient;
import com.ubuntucontinues.ubuntu.dto.request.Sender;
import com.ubuntucontinues.ubuntu.dto.response.BrevoMailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.REQUEST_MESSAGE;

@Service
public class UbuntuEmailService implements EmailService{

    @Value("${brevo.api.key}")
    private String apiKey;
    @Value("${brevo.api.url}")
    private String url;


    @Override
    public void sendMessage(String sender, String message, String recipient) {
        BrevoMailRequest request = createRequest(sender, message, recipient);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(url, entity, BrevoMailResponse.class);
    }

    private static BrevoMailRequest createRequest(String sender, String message, String recipient) {
        BrevoMailRequest request = new BrevoMailRequest();
        request.setSender(new Sender(sender, sender));
        request.setTo(List.of(new Recipient(recipient, recipient)));
        request.setHtmlContent(message);
        request.setSubject(REQUEST_MESSAGE);
        return request;
    }
}

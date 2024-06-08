package com.ubuntucontinues.ubuntu.services;

import com.ubuntucontinues.ubuntu.dto.requests.BrevoMailRequest;
import com.ubuntucontinues.ubuntu.dto.requests.Recipient;
import com.ubuntucontinues.ubuntu.dto.requests.Sender;
import com.ubuntucontinues.ubuntu.dto.responses.BrevoMailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.ubuntucontinues.ubuntu.util.AppUtils.REQUEST_MESSAGE;

@Service
public class
UbuntuEmailService implements EmailService{

    @Value("${brevo.api.key}")
    private String apiKey;
    @Value("${brevo.api.url}")
    private String url;


    @Override
    public void sendMessage(Sender sender, String message, List<Recipient> recipient, String subject) {
        BrevoMailRequest request = createRequest(sender, message, recipient, subject);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.postForEntity(url, entity, BrevoMailResponse.class));
    }

    private static BrevoMailRequest createRequest(Sender sender, String message, List<Recipient> recipient, String subject) {
        BrevoMailRequest request = new BrevoMailRequest();
        request.setSender(sender);
        request.setTo(recipient);
        request.setHtmlContent(message);
        request.setSubject(subject);
        return request;
    }
}

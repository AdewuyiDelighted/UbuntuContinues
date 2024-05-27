package com.ubuntucontinues.ubuntu.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary_name}")
    private String cloudName;
    @Value("${cloudinary_api_key}")
    private String apiKey;
    @Value("${cloudinary_secret_key}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> cloudinaryConfig = new HashMap<>();
        cloudinaryConfig.put("cloud_name", cloudName);
        cloudinaryConfig.put("api_key", apiKey);
        cloudinaryConfig.put("api_secret", apiSecret);
        return new Cloudinary(cloudinaryConfig);
    }
}

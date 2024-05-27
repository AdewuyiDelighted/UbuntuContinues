package com.ubuntucontinues.ubuntu.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Value("${community.manager.email}")
    public String communityManagerEmail;
    @Value("${community.manager.password}")
    public String communityManagerPassword;
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

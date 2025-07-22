package com.example.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    // This class is used to define application-wide configurations
    // such as beans, properties, etc.
    // We can use this class to define beans that will be used across the application
    // For example, we can define a RestTemplate bean here if needed

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
        // This bean can be used to make HTTP requests to third-party APIs
        // or any other RESTful services.

    }
}

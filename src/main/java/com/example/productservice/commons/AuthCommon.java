package com.example.productservice.commons;

import com.example.productservice.dtos.auth.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommon {
    private final RestTemplate restTemplate;

    public AuthCommon(
            RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }

    public UserDTO validateToken(String token) {
        String url = "http://localhost:8081/auth/validate";
        // Add token to headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try{
            ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    UserDTO.class
            );
            return responseEntity.getBody();
        }
        catch (Exception e) {
            return null;
        }

    }

}

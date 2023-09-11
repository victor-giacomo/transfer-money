package br.com.bank.transfer.service.impl;

import br.com.bank.transfer.dto.AuthorizationMessage;
import br.com.bank.transfer.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private RestTemplate restTemplate;

    @Value("${authorizationURL}")
    private String authorizationURL;

    @Value("${successMessage}")
    private String successMessage;

    public AuthorizationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean authorize() {
        ResponseEntity<AuthorizationMessage> response =
                restTemplate.getForEntity(authorizationURL, AuthorizationMessage.class);

        HttpStatusCode resultStatus = response.getStatusCode();
        AuthorizationMessage result = response.getBody();

        return resultStatus.equals(HttpStatus.OK) && result != null && result.getMessage().equals(successMessage);
    }
}

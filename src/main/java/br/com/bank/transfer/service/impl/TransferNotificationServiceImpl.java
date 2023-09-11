package br.com.bank.transfer.service.impl;

import br.com.bank.transfer.service.TransferNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TransferNotificationServiceImpl implements TransferNotificationService {

    private RestTemplate restTemplate;

    @Value("${notificationURL}")
    private String notificationURL;

    public TransferNotificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void send(String email) {
        restTemplate.postForEntity(notificationURL, email, Map.class);
    }
}

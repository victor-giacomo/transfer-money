package br.com.bank.commonkafka.consumer;

import br.com.bank.commonkafka.dto.NotificationStatus;
import br.com.bank.commonkafka.dto.TransferNotification;
import br.com.bank.commonkafka.feignclient.TransferNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TransferNotificationConsumer {

    @Value(value = "${send.email.url}")
    private String EMAIL_SENDER_URL;

    private final TransferNotificationService transferNotificationService;
    private final RestTemplate restTemplate;

    public TransferNotificationConsumer(TransferNotificationService transferNotificationService, RestTemplate restTemplate) {
        this.transferNotificationService = transferNotificationService;
        this.restTemplate = restTemplate;
    }

    @KafkaListener(topics = "${kafka.transfer.topic}", groupId = "${kafka.transfer.email.group}")
    @RetryableTopic(attempts = "${kafka.producer.retries}", backoff = @Backoff(30000L))
    public void execute(TransferNotification notification) {
        restTemplate.postForEntity(EMAIL_SENDER_URL, notification.getEmail(), Map.class);

        TransferNotification result = buildNotification(notification.getUuid(), NotificationStatus.PROCESSED);
        transferNotificationService.changeStatus(result);
    }

    @KafkaListener(topics = "${kafka.transfer.topic.dlt}", groupId = "${kafka.transfer.email.group}")
    public void executeDlt(TransferNotification notification) {
        TransferNotification result = buildNotification(notification.getUuid(), NotificationStatus.ERROR);
        transferNotificationService.changeStatus(result);
    }

    private TransferNotification buildNotification(String uuid, NotificationStatus status) {
        return TransferNotification.builder().uuid(uuid).status(status).build();
    }

}

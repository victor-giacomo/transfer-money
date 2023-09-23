package br.com.bank.commonkafka.controller;

import br.com.bank.commonkafka.dto.TransferNotification;
import br.com.bank.commonkafka.producer.KafkaDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Value("${kafka.transfer.topic}")
    private String MESSAGE_TRANSFER_TOPIC;

    private final KafkaDispatcher<TransferNotification> kafkaDispatcher;

    public MessageController(KafkaDispatcher<TransferNotification> kafkaDispatcher) {
        this.kafkaDispatcher = kafkaDispatcher;
    }

    @PostMapping(path = "/message/transfer-notification/send")
    public void send(@RequestBody TransferNotification value) {
        kafkaDispatcher.send(MESSAGE_TRANSFER_TOPIC, value.getUuid(), value);
    }
}
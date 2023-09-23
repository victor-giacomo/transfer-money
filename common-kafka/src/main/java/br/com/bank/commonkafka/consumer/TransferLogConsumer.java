package br.com.bank.commonkafka.consumer;

import br.com.bank.commonkafka.dto.TransferNotification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransferLogConsumer {

    @KafkaListener(topics = "${kafka.transfer.topic}", groupId = "${kafka.transfer.log.group}")
    public void execute(TransferNotification notification) {
        System.out.println("LOG: " + notification);
    }

}

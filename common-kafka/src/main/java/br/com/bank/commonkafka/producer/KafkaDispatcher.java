package br.com.bank.commonkafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaDispatcher<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public void send(String topic, String key, T value) {
        kafkaTemplate.send(topic, key, value);
    }
}
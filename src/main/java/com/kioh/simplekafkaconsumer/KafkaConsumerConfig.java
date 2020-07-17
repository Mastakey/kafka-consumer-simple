package com.kioh.simplekafkaconsumer;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@EnableKafka
@Configuration

public class KafkaConsumerConfig {
    @KafkaListener(topics = "simpletopic")
    public void processMessage(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
            @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

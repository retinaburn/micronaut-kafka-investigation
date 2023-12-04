package com.ibm.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;

@KafkaListener(threads = 2)
public class Consumer {
    private Logger log = LoggerFactory.getLogger(Consumer.class);

    @Inject
    Producer producer;

    @Topic("${topic.chat}")
    public void listener(ConsumerRecord<String, String> record) throws InterruptedException{
        log.debug("{}-{}:{} {}={}", record.topic(), record.partition(), record.offset(), record.key(), record.value());
        //Thread.sleep(1000);
        // producer.sendChat("1", "1");
        // producer.sendChat("2", "2");
    }
    
}

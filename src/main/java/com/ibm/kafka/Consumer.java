package com.ibm.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(threads = 2)
public class Consumer {
    private Logger log = LoggerFactory.getLogger(Consumer.class);

    @Topic("${topic.chat}")
    public void listener(ConsumerRecord<String, String> record){
        log.debug("{}-{}:{} {}={}", record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }
    
}

package com.ibm.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id="producer")
public interface Producer {
    @Topic("${topic.chat}")
    void sendChat(@KafkaKey String key, String value);
}

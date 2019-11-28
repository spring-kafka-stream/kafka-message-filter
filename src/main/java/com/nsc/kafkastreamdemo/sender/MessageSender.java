package com.nsc.kafkastreamdemo.sender;

import com.nsc.kafkastreamdemo.source.EventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(EventSource.class)
public class MessageSender {

    @Autowired
    EventSource eventSource;

    public void send(Object event) {
        Message<String> message = MessageBuilder
                .withPayload(event.toString())
                .setHeader(KafkaHeaders.MESSAGE_KEY, event.toString().getBytes())
                .build();
        eventSource.eventOutput().send(message);
    }
}

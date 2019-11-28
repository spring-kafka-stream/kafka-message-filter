package com.nsc.kafkastreamdemo.receiver;

import com.nsc.kafkastreamdemo.sink.EventSink;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaStreamBrancher;

@EnableBinding(EventSink.class)
public class MessageReceiver {

    @StreamListener(target = EventSink.EVENT_IN, condition = "headers['eventType']=='ID'")
    public void processIDEvent(KStream<String, String> eventStream) {

        Predicate<String, String> isId = (key, value) -> value.contains("id");
        Predicate<String, String> isName = (key, value) -> value.contains("name");
        KStream<String, String>[] forks = eventStream.branch(isId, isName);

        forks[0].mapValues((ValueMapper<String, String>) String::toUpperCase)
                .foreach((key, value) -> System.out.println("id " + value));

        forks[1].mapValues((ValueMapper<String, String>) String::toUpperCase)
                .foreach((key, value) -> System.out.println("name " + value));
    }
}

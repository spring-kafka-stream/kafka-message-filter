package com.nsc.kafkastreamdemo.sink;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface EventSink {

    String EVENT_IN ="eventIn";

    @Input(EVENT_IN)
    KStream<String, String> eventInput();
}

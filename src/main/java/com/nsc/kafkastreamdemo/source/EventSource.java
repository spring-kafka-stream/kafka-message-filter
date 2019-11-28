package com.nsc.kafkastreamdemo.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventSource {
    String EVENT_SOURCE = "eventOut";

    @Output(EVENT_SOURCE)
    MessageChannel eventOutput();
}

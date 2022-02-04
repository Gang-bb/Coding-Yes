package com.gangbb.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

//@Service
@Slf4j
public class TestStreamConsumer {

    @StreamListener(Sink.INPUT)
    public void receive(String messageBody) {
        log.info("通过stream收到的消息: {}", messageBody);
    }
}

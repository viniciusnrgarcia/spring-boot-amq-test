package br.com.vnrg.amq.springbootamqtest.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
public class OrderConsumer {

    @JmsListener(destination = "order.queue", concurrency = "1-5", containerFactory = "jmsListenerContainerFactory")
    public void consumer(@Payload String payload) {
        log.info(payload);
        throw new IllegalArgumentException();
    }
}

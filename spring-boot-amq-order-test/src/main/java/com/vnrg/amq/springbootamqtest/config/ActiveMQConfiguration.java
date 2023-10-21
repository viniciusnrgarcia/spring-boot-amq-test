package com.vnrg.amq.springbootamqtest.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import java.util.List;

@Configuration
@Slf4j
public class ActiveMQConfiguration {

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        var prefetchPolicy = new ActiveMQPrefetchPolicy();
        prefetchPolicy.setQueuePrefetch(1);
        prefetchPolicy.setQueueBrowserPrefetch(1);

        var redelivery = new RedeliveryPolicy();
        redelivery.setMaximumRedeliveries(5);
        redelivery.setRedeliveryDelay(10);
        // redelivery.setUseExponentialBackOff(true);

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "failover:(tcp://localhost:61616)?initialReconnectDelay=100");
        factory.setTrustedPackages(List.of("com.vnrg"));
        factory.setExceptionListener(e -> log.error("Error jms {} {} ", e.getErrorCode(), e.getMessage()));

        factory.setPrefetchPolicy(prefetchPolicy);
        factory.setMaxThreadPoolSize(5);
        factory.setNonBlockingRedelivery(false);
        factory.setRedeliveryPolicy(redelivery);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        var template = new JmsTemplate(this.connectionFactory());
        template.setPubSubDomain(false);
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.connectionFactory());
        return factory;
    }
}

package br.com.vnrg.amq.springbootamqtest.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

@Configuration
@Slf4j
public class ActiveMQConfiguration {


    @Bean
    public JmsTemplate jmsTemplate() {
        var template = new JmsTemplate(this.connectionFactory());
        template.setPubSubDomain(false);
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.connectionFactory());
        // factory.setReceiveTimeout(20000L);
        // factory.setRecoveryInterval(10000L);
        return factory;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        final var prefetchPolicy = new ActiveMQPrefetchPolicy();
        prefetchPolicy.setQueuePrefetch(10);
        prefetchPolicy.setQueueBrowserPrefetch(10);

        final var redelivery = new RedeliveryPolicy();
        redelivery.setMaximumRedeliveries(5);
        redelivery.setRedeliveryDelay(10);
        // redelivery.setUseExponentialBackOff(true);

        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
                "failover:(tcp://localhost:61616)?initialReconnectDelay=100");
        factory.setTrustedPackages(List.of("com.vnrg"));
        factory.setExceptionListener(e -> log.error("Error jms {} {} ", e.getErrorCode(), e.getMessage()));

        factory.setPrefetchPolicy(prefetchPolicy);
        factory.setMaxThreadPoolSize(5);
        factory.setNonBlockingRedelivery(true);
        factory.setRedeliveryPolicy(redelivery);
        return factory;
    }

}

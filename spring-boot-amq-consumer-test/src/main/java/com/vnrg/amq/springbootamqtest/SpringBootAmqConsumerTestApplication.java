package com.vnrg.amq.springbootamqtest;

import com.vnrg.amq.springbootamqtest.config.ActiveMQConfiguration;
import com.vnrg.amq.springbootamqtest.consumer.OrderConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@ComponentScan(basePackageClasses = {OrderConsumer.class, ActiveMQConfiguration.class})
public class SpringBootAmqConsumerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmqConsumerTestApplication.class, args);
    }

}

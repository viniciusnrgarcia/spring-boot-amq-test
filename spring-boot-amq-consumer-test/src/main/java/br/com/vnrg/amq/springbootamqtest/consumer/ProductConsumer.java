package br.com.vnrg.amq.springbootamqtest.consumer;

import com.google.gson.Gson;
import br.com.vnrg.amq.springbootamqtest.domain.Product;
import br.com.vnrg.amq.springbootamqtest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Configuration
public class ProductConsumer {

    private final ProductService service;

    private final Gson gson;

    public ProductConsumer(ProductService service, Gson gson) {
        this.service = service;
        this.gson = gson;
    }


    @JmsListener(destination = "product.queue", concurrency = "20-40", containerFactory = "jmsListenerContainerFactory")
    public void consumer(@Payload String payload) {
        try {
            var p = this.gson.fromJson(payload, Product.class);
            this.service.save(p);
            this.service.registration(p);
        } catch (Exception e) {
            log.error("Error consumer {}, exception: {} ", payload, e);
            throw e;
        }

    }
}

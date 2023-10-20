package br.com.vnrg.springbootamqtest.service;

import br.com.vnrg.springbootamqtest.controller.Product;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private final JmsTemplate jmsTemplate;
    private final Gson gson;

    public ProductService(JmsTemplate jmsTemplate, Gson gson) {
        this.jmsTemplate = jmsTemplate;
        this.gson = gson;
    }

    @Async
    public void send(final Product product) {
        try {
            this.jmsTemplate.convertAndSend("product.queue", gson.toJson(product));
        } catch (Exception e) {
            log.error("Error producer {0} ", e);
        }
    }
}

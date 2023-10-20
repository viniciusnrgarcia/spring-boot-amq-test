package br.com.vnrg.springbootamqtest.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {

    private final JmsTemplate jmsTemplate;
    private final Gson gson;

    public OrderController(JmsTemplate jmsTemplate, Gson gson) {
        this.jmsTemplate = jmsTemplate;
        this.gson = gson;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Validated Order order) {
        try {
            log.info("Creating order {} ", order);
            for (int i = 0; i < order.getCount(); i++) {
                this.jmsTemplate.convertAndSend("order.queue", gson.toJson(order));
            }
        } catch (Exception e) {
            log.error("Error producer {0}", e);
        }
        return ResponseEntity.ok().build();
    }
}

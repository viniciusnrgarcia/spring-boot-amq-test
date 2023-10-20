package br.com.vnrg.springbootamqtest.controller;

import br.com.vnrg.springbootamqtest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam Integer total) {
        log.info("Creating products {} ", total);
        for (int i = 0; i < total; i++) {
            this.service.send(new Product(UUID.randomUUID().toString(), "Product name " + i, "Product description " + i, i));
        }
        return ResponseEntity.ok().build();
    }
}

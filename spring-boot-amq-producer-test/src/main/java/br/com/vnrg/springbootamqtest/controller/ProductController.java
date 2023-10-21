package br.com.vnrg.springbootamqtest.controller;

import br.com.vnrg.springbootamqtest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> create(@RequestBody Product p) {
        // for (int i = 0; i < total; i++) {
        // p.setId(UUID.randomUUID().toString());
        this.service.send(p);
        // }
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/perf")
    public ResponseEntity<String> create(@RequestBody Product p, @RequestParam Integer total) {
        for (int i = 0; i < total; i++) {
            p.setId(UUID.randomUUID().toString());
            this.service.send(p);
        }
        return ResponseEntity.ok().build();
    }
}

package br.com.vnrg.amq.springbootamqtest.service;

import br.com.vnrg.amq.springbootamqtest.domain.Product;
import br.com.vnrg.amq.springbootamqtest.repository.ProductRepository;
import br.com.vnrg.amq.springbootamqtest.repository.mapper.ProductMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
@Slf4j
public class ProductService {

    private final JmsTemplate jmsTemplate;
    private final Gson gson;

    private final RestTemplate restTemplate;

    private final ProductRepository repository;

    public ProductService(JmsTemplate jmsTemplate, Gson gson, RestTemplate restTemplate, ProductRepository repository) {
        this.jmsTemplate = jmsTemplate;
        this.gson = gson;
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    // @Async
    public void registration(final Product product) {
        try {
            UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/products");

            HttpHeaders headers = new HttpHeaders();
            headers.add("uuid", UUID.randomUUID().toString());
            HttpEntity<Product> entity = new HttpEntity<>(product, headers);

            var response = this.restTemplate.exchange(uri.toUriString(), HttpMethod.POST, entity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                var statusCode = response.getStatusCode();
                var responseBody = response.getBody();
                log.error("Error http request {}, status code: {}, response body: {} ", product.toString(), statusCode, responseBody);
            }

        } catch (Exception e) {
            log.error("Error producer {0} ", e);
            throw e;
        }
    }

    public void save(Product p) {
        try {
            var entity = ProductMapper.INSTANCE.toEntity(p);
            this.repository.save(entity);

        } catch (DuplicateKeyException de) {
            log.error("Error duplicate key exception {} ", de);
            p.setId(UUID.randomUUID().toString());
            this.save(p);

        } catch (Exception e) {
            log.error("Error save product {} ", p);
            throw e;
        }
    }
}

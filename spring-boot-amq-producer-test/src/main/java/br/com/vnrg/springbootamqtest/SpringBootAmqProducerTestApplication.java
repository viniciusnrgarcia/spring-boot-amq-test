package br.com.vnrg.springbootamqtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJms
@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {"br.com.vnrg.*"})
public class SpringBootAmqProducerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAmqProducerTestApplication.class, args);
	}

}

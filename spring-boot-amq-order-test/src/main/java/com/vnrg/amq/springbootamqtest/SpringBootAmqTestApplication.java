package com.vnrg.amq.springbootamqtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@ComponentScan(basePackages = {"com.vnrg.*"})
public class SpringBootAmqTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAmqTestApplication.class, args);
	}

}

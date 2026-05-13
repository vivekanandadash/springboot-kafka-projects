package com.order_service_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class OrderServiceExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceExampleApplication.class, args);
	}

}

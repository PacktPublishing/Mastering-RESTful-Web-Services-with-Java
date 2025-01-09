package com.packt.ordermanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class OrderManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApiApplication.class, args);
	}

}

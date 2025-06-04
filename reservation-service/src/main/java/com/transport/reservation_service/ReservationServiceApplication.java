package com.transport.reservation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
public class ReservationServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	// ✅ Déclaration du client HTTP
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

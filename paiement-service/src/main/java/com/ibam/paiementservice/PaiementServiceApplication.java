package com.ibam.paiementservice;

import com.ibam.paiementservice.entities.Payment;
import com.ibam.paiementservice.entities.model.Reservation;
import com.ibam.paiementservice.feign.ReservationRestClient;
import com.ibam.paiementservice.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Collection;

@SpringBootApplication
@EnableFeignClients
public class PaiementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaiementServiceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(PaymentRepository paymentRepository,
//										ReservationRestClient reservationRestClient) {
//		Collection<Reservation> reservations = reservationRestClient.getAllReservation().getContent();
//
//		return args -> {
//			reservations.forEach(reservation -> {
//				Payment payment = Payment.builder()
//						.createdAt(LocalDateTime.now())
//						.currency("Fcfa")
//						.fretId(reservation.getId())
//						.build();
//				paymentRepository.save(payment);
//
//			});
//		};
//	}
}

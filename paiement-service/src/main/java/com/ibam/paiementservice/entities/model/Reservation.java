package com.ibam.paiementservice.entities.model;

import com.ibam.paiementservice.entities.Payment;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Reservation {

    private Long id;

    private Long userId;

    private Long tripId;

    private LocalDateTime reservationDate;

    private ReservationStatus status; // PENDING, CONFIRMED, CANCELLED, PAID

    private Double totalAmount;

    private boolean paid;

//    private List<ReservationItem> items ;

    private Payment payment;

    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

}

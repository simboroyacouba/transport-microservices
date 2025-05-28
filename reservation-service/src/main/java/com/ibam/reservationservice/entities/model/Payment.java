package com.ibam.reservationservice.entities.model;

import com.ibam.reservationservice.entities.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Payment {

    private Long id;

    @Transient private User user;
    private Reservation reservation;
    private Long fretId;

    private String reference;
    private Double amount;
    private String currency;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    private String paymentMethod;
    private String gatewayResponse;

}

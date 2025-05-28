package com.ibam.paiementservice.entities;

import com.ibam.paiementservice.entities.model.Reservation;
import com.ibam.paiementservice.entities.model.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Transient
    private User user;

    private Long reservationId;

    @Transient
    private Reservation reservation;

    private String reference;
    private Double amount;
    private String currency;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    private String paymentMethod;
    private String gatewayResponse;

}

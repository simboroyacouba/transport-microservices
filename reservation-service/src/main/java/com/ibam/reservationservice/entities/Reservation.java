package com.ibam.reservationservice.entities;

import com.ibam.reservationservice.entities.model.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long tripId;

    private LocalDateTime reservationDate;

    private ReservationStatus status; // PENDING, CONFIRMED, CANCELLED, PAID

    private Double totalAmount;

    private boolean paid;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationItem> items ;

    @Transient private Payment payment;

    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

}

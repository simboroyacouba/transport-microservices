package com.transport.reservation_service.dto;

import com.transport.reservation_service.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDetails {
    private Long id;
    private Long userId;
    private Long trajetId;
    private SeatResponse siege;
    private String reference;
    private PaymentStatus paymentStatus;
    private LocalDateTime dateReservation;
    private LocalDateTime dateAnnulation;
    private Boolean estAnnulee;
}
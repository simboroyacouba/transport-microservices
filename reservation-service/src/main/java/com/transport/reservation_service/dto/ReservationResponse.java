package com.transport.reservation_service.dto;

import com.transport.reservation_service.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long id;
    private Long userId;
    private Long trajetId;
    private Long siegeId;
    private String reference;
    private LocalDateTime dateReservation;
    private LocalDateTime dateAnnulation;
    private LocalDateTime dateModification;
    private Boolean estAnnulee;
    private String paymentReference;
    private PaymentStatus paymentStatus;

    public String getStatut() {
        return estAnnulee ? "ANNULEE" : paymentStatus != null ? paymentStatus.toString() : "INCONNU";
    }
}
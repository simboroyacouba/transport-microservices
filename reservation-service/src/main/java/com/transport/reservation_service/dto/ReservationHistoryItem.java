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
public class ReservationHistoryItem {
    private Long reservationId;
    private String reference;
    private Integer siegeNumber;
    private String trajetName;
    private boolean cancelled;
    private LocalDateTime reservationDate;
    private LocalDateTime cancellationDate;
    private PaymentStatus paymentStatus;

    public String getStatus() {
        if (paymentStatus == null) {
            return cancelled ? "CANCELLED" : "UNKNOWN";
        }
        return cancelled ? "CANCELLED" : paymentStatus.toString();
    }
}
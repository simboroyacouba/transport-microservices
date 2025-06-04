package com.transport.reservation_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Payment {
    private Long id;
    private Long userId;
    private Long reservationId;
    private String reference;
    private Double amount;
    private String currency;
    private String status; // "INITIATED", "SUCCESS", "FAILED"
    private LocalDateTime createdAt;
    private String paymentMethod;
}
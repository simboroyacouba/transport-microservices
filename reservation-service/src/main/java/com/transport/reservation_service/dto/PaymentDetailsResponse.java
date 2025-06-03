package com.transport.reservation_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentDetailsResponse {
    private String paymentReference;
    private Double amount;
    private String currency;
    private LocalDateTime paymentDate;
    private String status;
    private String gatewayTransactionId;
}
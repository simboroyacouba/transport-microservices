package com.transport.reservation_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentConfirmationRequest {
    @NotBlank(message = "La référence de paiement est obligatoire")
    private String paymentReference;
    
    @NotBlank(message = "Le statut est obligatoire")
    private String status; // "SUCCESS", "FAILED"
}
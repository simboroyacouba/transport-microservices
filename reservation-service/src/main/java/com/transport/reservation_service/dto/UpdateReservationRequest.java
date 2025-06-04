package com.transport.reservation_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateReservationRequest {
    @NotNull(message = "Le nouveau siège est obligatoire")
    private Long nouveauSiegeId;
}
package com.transport.reservation_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {
    @NotNull(message = "L'ID utilisateur est obligatoire")
    private Long userId;
    
    @NotNull(message = "L'ID trajet est obligatoire")
    private Long trajetId;
    
    @NotNull(message = "L'ID siège est obligatoire")
    private Long siegeId;
}
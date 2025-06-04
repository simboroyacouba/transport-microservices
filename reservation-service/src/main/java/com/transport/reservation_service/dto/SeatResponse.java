package com.transport.reservation_service.dto;

import com.transport.reservation_service.model.StatutSiege;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // pour l’utilisation du builder dans toDto
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse {

    private Long id;
    private Integer numeroSiege;  // correspond à Siege.numeroSiege
    private String voiture;       // correspond à Siege.voiture (enum en string)
    private String position;      // correspond à Siege.position (enum en string)
    private StatutSiege statut;   // correspond à Siege.statut (enum)
}

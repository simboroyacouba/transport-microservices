package com.transport.reservation_service.dto;

import com.transport.reservation_service.model.StatutSiege;
import lombok.Data;

@Data
public class CreateSeatRequest {

    private Integer numeroSiege;  // correspond à Siege.numeroSiege
    private String voiture;       // correspond à Siege.voiture (enum en string)
    private String position;      // correspond à Siege.position (enum en string)
    private StatutSiege statut;   // correspond à Siege.statut (enum)
}

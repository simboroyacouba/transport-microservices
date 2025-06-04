package com.transport.reservation_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "siege")  
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Siege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_siege", nullable = false)
    private Integer numeroSiege;  // Numéro du siège (ex: 1, 2, 3...)

    @Enumerated(EnumType.STRING)
    @Column(name = "voiture", nullable = false, length = 20)
    private Voiture voiture;      // Voiture ou section (ex: "A", "B", "Avant", "Arrière")

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false, length = 20)
    private PositionSiege position; // Position ("fenêtre", "couloir", "milieu")

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutSiege statut;   // Statut du siège
}

package com.transport.reservation_service.service.impl;

import com.transport.reservation_service.dto.CreateSeatRequest;
import com.transport.reservation_service.dto.SeatResponse;
import com.transport.reservation_service.dto.UpdateSeatRequest;
import com.transport.reservation_service.model.*;
import com.transport.reservation_service.repository.SiegeRepository;
import com.transport.reservation_service.service.SiegeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiegeServiceImpl implements SiegeService {

    private final SiegeRepository siegeRepository;

    public SiegeServiceImpl(SiegeRepository siegeRepository) {
        this.siegeRepository = siegeRepository;
    }

    @Override
    public SeatResponse createSeat(CreateSeatRequest request) {
        // Vérifier l’unicité du numéro de siège (si nécessaire)
        if (siegeRepository.existsByNumeroSiege(request.getNumeroSiege())) {
            throw new IllegalArgumentException("Ce numéro de siège existe déjà.");
        }

        // Création du siège
        Siege siege = Siege.builder()
                .numeroSiege(request.getNumeroSiege())
                .voiture(Voiture.valueOf(request.getVoiture().toUpperCase())) // Conversion en Enum
                .position(PositionSiege.valueOf(request.getPosition().toUpperCase())) // Conversion en Enum
                .statut(request.getStatut())
                .build();

        return toDto(siegeRepository.save(siege));
    }

    @Override
    public SeatResponse updateSeat(Long siegeId, UpdateSeatRequest request) {
        Siege siege = siegeRepository.findById(siegeId)
                .orElseThrow(() -> new IllegalArgumentException("Siège introuvable"));

        // Mise à jour des champs
        siege.setNumeroSiege(request.getNumeroSiege());
        siege.setVoiture(Voiture.valueOf(request.getVoiture().toUpperCase()));
        siege.setPosition(PositionSiege.valueOf(request.getPosition().toUpperCase()));
        siege.setStatut(request.getStatut());

        return toDto(siegeRepository.save(siege));
    }

    @Override
    public void deleteSeat(Long siegeId) {
        if (!siegeRepository.existsById(siegeId)) {
            throw new IllegalArgumentException("Le siège à supprimer n'existe pas.");
        }
        siegeRepository.deleteById(siegeId);
    }

    @Override
    public SeatResponse getSeatById(Long siegeId) {
        Siege siege = siegeRepository.findById(siegeId)
                .orElseThrow(() -> new IllegalArgumentException("Siège introuvable"));
        return toDto(siege);
    }

    @Override
    public List<SeatResponse> getAllSeats() {
        return siegeRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 🔁 Conversion Entity -> DTO
    private SeatResponse toDto(Siege siege) {
        return SeatResponse.builder()
                .id(siege.getId())
                .numeroSiege(siege.getNumeroSiege())
                .voiture(siege.getVoiture().name())
                .position(siege.getPosition().name())
                .statut(siege.getStatut())
                .build();
    }
}

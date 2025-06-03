package com.transport.reservation_service.service.impl;

import com.transport.reservation_service.dto.*;
import com.transport.reservation_service.model.Reservation;
import com.transport.reservation_service.model.PaymentStatus;
import com.transport.reservation_service.model.Siege;
import com.transport.reservation_service.repository.ReservationRepository;
import com.transport.reservation_service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public ReservationResponse createReservation(CreateReservationRequest request) {
        // Validation
        if (reservationRepository.existsByTrajetIdAndSiegeIdAndEstAnnuleeFalse(
            request.getTrajetId(), request.getSiegeId())) {
            throw new IllegalStateException("Ce siège est déjà réservé");
        }

        // Création
        Reservation reservation = Reservation.builder()
            .userId(request.getUserId())
            .trajetId(request.getTrajetId())
            .siege(Siege.builder().id(request.getSiegeId()).build())
            .paymentStatus(PaymentStatus.PENDING)
            .dateReservation(LocalDateTime.now())
            .estAnnulee(false)
            .build();

        Reservation savedReservation = reservationRepository.save(reservation);

        return mapToResponse(savedReservation);
    }

    @Override
    public ReservationResponse updateReservation(Long id, UpdateReservationRequest request) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable"));

        if (request.getNouveauSiegeId() != null) {
            reservation.setSiege(Siege.builder().id(request.getNouveauSiegeId()).build());
        }

        reservation.setDateModification(LocalDateTime.now());
        Reservation updatedReservation = reservationRepository.save(reservation);

        return mapToResponse(updatedReservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable"));
        
        reservation.setEstAnnulee(true);
        reservation.setPaymentStatus(PaymentStatus.CANCELLED);
        reservation.setDateAnnulation(LocalDateTime.now());
        reservation.setDateModification(LocalDateTime.now());
        
        reservationRepository.save(reservation);
    }

    @Override
    public ReservationResponse confirmPayment(String paymentReference) {
        Reservation reservation = reservationRepository.findByPaymentReference(paymentReference)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable"));

        reservation.setPaymentReference(paymentReference);
        reservation.setPaymentStatus(PaymentStatus.COMPLETED);
        reservation.setDateModification(LocalDateTime.now());

        return mapToResponse(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDetails getReservationDetails(Long id) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable"));
        
        return mapToDetails(reservation);
    }

    @Override
    public List<ReservationHistoryItem> getUserReservationHistory(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserIdOrderByDateReservationDesc(userId);
        return mapToHistoryItems(reservations);
    }

    // Helper methods
    private ReservationResponse mapToResponse(Reservation reservation) {
        return ReservationResponse.builder()
            .id(reservation.getId())
            .userId(reservation.getUserId())
            .trajetId(reservation.getTrajetId())
            .siegeId(reservation.getSiege() != null ? reservation.getSiege().getId() : null)
            .reference(reservation.getReference())
            .paymentStatus(reservation.getPaymentStatus())
            .dateReservation(reservation.getDateReservation())
            .dateAnnulation(reservation.getDateAnnulation())
            .estAnnulee(reservation.getEstAnnulee())
            .build();
    }

    private ReservationDetails mapToDetails(Reservation reservation) {
        return ReservationDetails.builder()
            .id(reservation.getId())
            .userId(reservation.getUserId())
            .trajetId(reservation.getTrajetId())
            .siege(mapToSeatResponse(reservation.getSiege()))
            .reference(reservation.getReference())
            .paymentStatus(reservation.getPaymentStatus())
            .dateReservation(reservation.getDateReservation())
            .dateAnnulation(reservation.getDateAnnulation())
            .estAnnulee(reservation.getEstAnnulee())
            .build();
    }

    private SeatResponse mapToSeatResponse(Siege siege) {
        if (siege == null) return null;
        
        return SeatResponse.builder()
            .id(siege.getId())
            .numeroSiege(siege.getNumeroSiege())
            .voiture(siege.getVoiture().name())
            .position(siege.getPosition().name())
            .statut(siege.getStatut())
            .build();
    }

    private List<ReservationHistoryItem> mapToHistoryItems(List<Reservation> reservations) {
        return reservations.stream()
            .map(this::mapToHistoryItem)
            .collect(Collectors.toList());
    }

    private ReservationHistoryItem mapToHistoryItem(Reservation reservation) {
        return ReservationHistoryItem.builder()
            .reservationId(reservation.getId())
            .reference(reservation.getReference())
            .siegeNumber(reservation.getSiege() != null ? reservation.getSiege().getNumeroSiege() : null)
            .trajetName("Trajet #" + reservation.getTrajetId())
            .cancelled(reservation.getEstAnnulee())
            .reservationDate(reservation.getDateReservation())
            .cancellationDate(reservation.getDateAnnulation())
            .paymentStatus(reservation.getPaymentStatus())
            .build();
    }
}
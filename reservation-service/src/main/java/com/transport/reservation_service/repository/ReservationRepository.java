package com.transport.reservation_service.repository;

import com.transport.reservation_service.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Vérifie si un siège est déjà réservé sur un trajet (non annulé)
    boolean existsByTrajetIdAndSiegeIdAndEstAnnuleeFalse(Long trajetId, Long siegeId);

    // Récupère les réservations d'un utilisateur triées par date
    List<Reservation> findByUserIdOrderByDateReservationDesc(Long userId);

    // Trouve les réservations actives pour un trajet
    List<Reservation> findByTrajetIdAndEstAnnuleeFalse(Long trajetId);

    // Recherche par référence de paiement
    Optional<Reservation> findByPaymentReference(String paymentReference);

    // Requête custom avec jointure
    @Query("SELECT r FROM Reservation r JOIN FETCH r.siege WHERE r.userId = :userId")
    List<Reservation> findWithSiegeByUserId(@Param("userId") Long userId);

    // Statistiques entre deux dates
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.dateReservation BETWEEN :start AND :end")
    long countBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
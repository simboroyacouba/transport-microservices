package com.transport.reservation_service.repository;

import com.transport.reservation_service.model.Siege;
import com.transport.reservation_service.model.StatutSiege;
import com.transport.reservation_service.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiegeRepository extends JpaRepository<Siege, Long> {

    // 🔍 Tous les sièges avec un statut donné
    List<Siege> findByStatut(StatutSiege statut);

    // 🔍 Vérifier si un numéro de siège existe déjà (unique, global)
    boolean existsByNumeroSiege(Integer numeroSiege);

    // 🔍 Récupérer les sièges d’une voiture spécifique
    List<Siege> findByVoiture(Voiture voiture);
}

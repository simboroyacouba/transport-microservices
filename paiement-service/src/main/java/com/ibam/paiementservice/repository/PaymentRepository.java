package com.ibam.paiementservice.repository;

import com.ibam.paiementservice.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


//@RepositoryRestResource
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Trouver un paiement par ID
    Optional<Payment> findById(Long id);

    // Récupérer une page de paiements
    Page<Payment> findAll(Pageable pageable);

    // Sauvegarder un paiement (création ou mise à jour)
    Payment save(Payment payment);

    // Vérifier si un paiement existe par ID
    boolean existsById(Long id);

    // Supprimer un paiement par ID
    void deleteById(Long id);
}

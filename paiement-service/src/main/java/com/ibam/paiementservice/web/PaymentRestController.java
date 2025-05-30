package com.ibam.paiementservice.web;

import com.ibam.paiementservice.entities.Payment;
import com.ibam.paiementservice.entities.model.Reservation;
import com.ibam.paiementservice.entities.model.User;
import com.ibam.paiementservice.feign.ReservationRestClient;
import com.ibam.paiementservice.feign.UserRestClient;
import com.ibam.paiementservice.repository.PaymentRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/paiements")
public class PaymentRestController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentRestController.class);
    @Autowired
    private PaymentRepository  paymentRepository;

    @Autowired
    private ReservationRestClient reservationRestClient;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private PagedResourcesAssembler<Payment> pagedResourcesAssembler;




    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        try {
            Optional<Payment> optionalPayment = paymentRepository.findById(id);
            if (!optionalPayment.isPresent()) {
                logger.warn("Paiement introuvable avec l'ID : {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Paiement introuvable avec l'ID : " + id));
            }
            Payment payment = optionalPayment.get();
            payment.setReservation(getReservation(payment.getReservationId()));
            payment.setUser(getUser(payment.getUserId()));
            return ResponseEntity.ok(payment);

        } catch (Exception e) {
            logger.error("Erreur interne lors de la récupération du paiement : ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message","Erreur interne du serveur."));
        }
    }


    @GetMapping()
    public ResponseEntity<?> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        try {
            Page<Payment> payments = paymentRepository.findAll(PageRequest.of(page, size));

            payments.forEach(p -> {
                try {
                    p.setReservation(getReservation(p.getReservationId()));
                } catch (Exception e) {
                    logger.error("Erreur lors de l'appel à reservation-service pour le paiement ID {} : {}", p.getId(), e.getMessage());
                    p.setReservation(null);
                }

                try {
                    p.setUser(getUser(p.getUserId()));
                } catch (Exception e) {
                    logger.error("Erreur lors de l'appel à user-service pour le paiement ID {} : {}", p.getId(), e.getMessage());
                    p.setUser(null);
                }
            });

            PagedModel<EntityModel<Payment>> model = pagedResourcesAssembler.toModel(payments);
            return ResponseEntity.ok(model);

        } catch (Exception e) {
            logger.error("Erreur interne lors de la récupération des paiements : ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message","Erreur interne du serveur."));
        }
    }

    @PostMapping()
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        try {
            // Sauvegarde du paiement en base
            Payment savedPayment = paymentRepository.save(payment);

//            try {
//                // Appel au service de réservation
//                savedPayment.setReservation(reservationRestClient.getReservation(payment.getReservationId()));
//            } catch (Exception e) {
//                logger.error("Erreur lors de l'appel à reservation-service pour la création du paiement ID {} : {}", savedPayment.getId(), e.getMessage());
//                savedPayment.setReservation(null);
//            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);

        } catch (Exception e) {
            logger.error("Erreur interne lors de la création du paiement : ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message","Erreur lors de la création du paiement."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        try {
            Optional<Payment> optionalPayment = paymentRepository.findById(id);
            if (!optionalPayment.isPresent()) {
                logger.warn("Paiement introuvable pour la mise à jour, ID : {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Paiement introuvable avec l'ID : " + id));
            }

            Payment existingPayment = optionalPayment.get();
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setReservationId(updatedPayment.getReservationId());

            Payment savedPayment = paymentRepository.save(existingPayment);

            try {
                savedPayment.setReservation(reservationRestClient.getReservation(savedPayment.getReservationId()));
            } catch (Exception e) {
                logger.error("Erreur lors de l'appel à reservation-service pour la mise à jour du paiement ID {} : {}", id, e.getMessage());
                savedPayment.setReservation(null);
            }

            return ResponseEntity.ok(savedPayment);

        } catch (Exception e) {
            logger.error("Erreur interne lors de la mise à jour du paiement ID {} : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur interne lors de la mise à jour du paiement avec l'ID : " + id));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id) {
        try {
            if (!paymentRepository.existsById(id)) {
                logger.warn("Tentative de suppression d’un paiement inexistant avec ID : {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Paiement introuvable avec l'ID : " + id));
            }

            paymentRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content

        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du paiement ID {} : {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur lors de la suppression du paiement avec l'ID : " + id));
        }
    }

    @Hidden
    public Reservation getReservation(Long reservationId) {
        try {
            Reservation res = reservationRestClient.getReservation(reservationId);
            return res;
        } catch (Exception e) {
            logger.error("Erreur lors de l'appel à reservation-service : ", e.getMessage());
            return null;
        }
    }

    @Hidden
    public User getUser(Long userId) {
        try {
            User user = userRestClient.getUser(userId);
            return user;
        } catch (Exception e) {
            logger.error("Erreur lors de l'appel à reservation-service :", e.getMessage());
            return null;
        }
    }

}

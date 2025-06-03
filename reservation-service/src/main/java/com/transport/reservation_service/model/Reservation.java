package com.transport.reservation_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "trajet_id", nullable = false)
    private Long trajetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siege_id")
    private Siege siege;

    @Column(name = "date_reservation", nullable = false)
    private LocalDateTime dateReservation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @Column(name = "date_annulation")
    private LocalDateTime dateAnnulation;

    @Column(name = "est_annulee", nullable = false)
    @Builder.Default
    private Boolean estAnnulee = false;

    @Column(name = "payment_reference", unique = true)
    private String paymentReference;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    @Builder.Default
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "reference", unique = true)
    private String reference;

    // Méthodes métier
    public void confirmPayment(String paymentRef) {
        this.paymentReference = paymentRef;
        this.paymentStatus = PaymentStatus.COMPLETED;
        this.dateModification = LocalDateTime.now();
    }

    public void cancelReservation() {
        this.paymentStatus = PaymentStatus.FAILED;
        this.estAnnulee = true;
        this.dateAnnulation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    protected void updateTimestamps() {
        if (this.dateReservation == null) {
            this.dateReservation = LocalDateTime.now();
        }
        this.dateModification = LocalDateTime.now();
    }
}
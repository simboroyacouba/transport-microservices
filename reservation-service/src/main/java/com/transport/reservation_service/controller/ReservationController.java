package com.transport.reservation_service.controller;

import com.transport.reservation_service.dto.*;
import com.transport.reservation_service.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
            @Valid @RequestBody CreateReservationRequest request) {
        ReservationResponse response = reservationService.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(
            @PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<ReservationResponse> confirmPayment(
            @Valid @RequestBody PaymentConfirmationRequest request) {
        ReservationResponse response = reservationService.confirmPayment(request.getPaymentReference());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationHistoryItem>> getUserReservations(
            @PathVariable Long userId) {
        List<ReservationHistoryItem> history = reservationService.getUserReservationHistory(userId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDetails> getReservationDetails(
            @PathVariable Long id) {
        ReservationDetails details = reservationService.getReservationDetails(id);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReservationRequest request) {
        ReservationResponse response = reservationService.updateReservation(id, request);
        return ResponseEntity.ok(response);
    }
}
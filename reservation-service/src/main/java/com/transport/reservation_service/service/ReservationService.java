package com.transport.reservation_service.service;

import com.transport.reservation_service.dto.*;
import com.transport.reservation_service.model.Reservation;
import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(CreateReservationRequest request);
    ReservationResponse updateReservation(Long id, UpdateReservationRequest request);
    void cancelReservation(Long reservationId);
    ReservationResponse confirmPayment(String paymentReference);
    List<ReservationHistoryItem> getUserReservationHistory(Long userId);
    ReservationDetails getReservationDetails(Long id);
}
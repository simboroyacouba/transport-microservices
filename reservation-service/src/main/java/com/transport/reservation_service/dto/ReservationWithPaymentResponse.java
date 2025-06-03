package com.transport.reservation_service.dto;

import lombok.Data;

@Data
public class ReservationWithPaymentResponse {
    private ReservationResponse reservation;
    private PaymentDetailsResponse paymentDetails;
}
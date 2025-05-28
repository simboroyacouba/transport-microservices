package com.ibam.paiementservice.entities.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ReservationItem {

    private Long id;

    private String seatNumber;

    private String passengerName;

    private Double price;

    private Reservation reservation;

}


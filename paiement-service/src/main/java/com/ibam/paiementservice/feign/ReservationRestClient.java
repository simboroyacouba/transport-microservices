package com.ibam.paiementservice.feign;

import com.ibam.paiementservice.entities.model.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "reservation-service")
public interface ReservationRestClient {

    @GetMapping("api/reservations/{id}")
    Reservation getReservation(@PathVariable Long id);


    @GetMapping("api/reservations")
    PagedModel<Reservation> getAllReservation();
}

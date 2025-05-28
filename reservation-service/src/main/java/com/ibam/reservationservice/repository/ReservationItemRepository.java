package com.ibam.reservationservice.repository;

import com.ibam.reservationservice.entities.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {
}

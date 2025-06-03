package com.transport.reservation_service.service;

import com.transport.reservation_service.dto.CreateSeatRequest;
import com.transport.reservation_service.dto.SeatResponse;
import com.transport.reservation_service.dto.UpdateSeatRequest;

import java.util.List;

public interface SiegeService {

    SeatResponse createSeat(CreateSeatRequest request);

    SeatResponse updateSeat(Long siegeId, UpdateSeatRequest request);

    void deleteSeat(Long siegeId);

    SeatResponse getSeatById(Long siegeId);

    List<SeatResponse> getAllSeats();

}

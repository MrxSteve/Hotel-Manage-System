package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationServiceModel;

import java.util.List;
import java.util.UUID;

public interface ReservationServiceRepositoryPort {
    ReservationServiceModel save(ReservationServiceModel model);
    List<ReservationServiceModel> findByReservationId(UUID reservationId);
    void deleteByReservationId(UUID reservationId);
    void deleteById(UUID reservationId, Integer serviceId);
}

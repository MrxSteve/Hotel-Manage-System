package com.devsteve.hotel_manage_system.application.ports.input.reservation.extra;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationServiceModel;

import java.util.List;
import java.util.UUID;

public interface GetReservationServicesUseCase {
    List<ReservationServiceModel> getByReservationId(UUID reservationId);
}

package com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations;

import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;

public interface CreateReservationUseCase {
    ReservationModel save(ReservationModel model);
}

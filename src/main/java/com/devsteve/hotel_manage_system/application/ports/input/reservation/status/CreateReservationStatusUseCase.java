package com.devsteve.hotel_manage_system.application.ports.input.reservation.status;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;

public interface CreateReservationStatusUseCase {
    ReservationStatusModel save(ReservationStatusModel reservationStatusModel);
}

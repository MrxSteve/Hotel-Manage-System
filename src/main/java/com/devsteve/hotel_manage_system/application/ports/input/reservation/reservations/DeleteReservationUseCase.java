package com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations;

import java.util.UUID;

public interface DeleteReservationUseCase {
    void deleteById(UUID id);
}

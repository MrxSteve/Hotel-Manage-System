package com.devsteve.hotel_manage_system.application.ports.input.reservation.extra;

import java.util.UUID;

public interface RemoveReservationServiceUseCase {
    void remove(UUID reservationId, Integer serviceId);
}

package com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations;

import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;

import javax.annotation.Nullable;
import java.util.UUID;

public interface ChangeReservationStatusUseCase {
    ReservationModel changeStatus(UUID reservationId, Integer nuevoEstadoId, @Nullable String comentario);
}

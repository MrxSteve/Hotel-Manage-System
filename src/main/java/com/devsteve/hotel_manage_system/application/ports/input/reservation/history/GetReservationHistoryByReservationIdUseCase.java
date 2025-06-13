package com.devsteve.hotel_manage_system.application.ports.input.reservation.history;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;

import java.util.List;
import java.util.UUID;

public interface GetReservationHistoryByReservationIdUseCase {
    List<ReservationHistoryModel> getHistoryByReservationId(UUID reservationId);
}

package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.history.GetReservationHistoryByReservationIdUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationHistoryRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;

import java.util.List;
import java.util.UUID;

public class ReservationHistoryService implements GetReservationHistoryByReservationIdUseCase {
    private final ReservationHistoryRepositoryPort reservationHistoryRepositoryPort;

    public ReservationHistoryService(ReservationHistoryRepositoryPort reservationHistoryRepositoryPort) {
        this.reservationHistoryRepositoryPort = reservationHistoryRepositoryPort;
    }

    @Override
    public List<ReservationHistoryModel> getHistoryByReservationId(UUID reservationId) {
        return reservationHistoryRepositoryPort.findByReservationId(reservationId);
    }
}

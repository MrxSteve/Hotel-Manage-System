package com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations;

import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;

import java.util.List;

public interface GetAllReservationsUseCase {
    List<ReservationModel> findAll(int page, int size);
}

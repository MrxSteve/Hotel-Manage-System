package com.devsteve.hotel_manage_system.application.ports.input.reservation.status;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;

import java.util.List;

public interface GetAllReservationStatusUseCase {
    List<ReservationStatusModel> findAll(int page, int size);
}

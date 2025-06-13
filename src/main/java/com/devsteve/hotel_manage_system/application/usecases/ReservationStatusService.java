package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.status.*;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationStatusRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;

import java.util.List;

public class ReservationStatusService implements
        CreateReservationStatusUseCase,
        FindByIdReservationStatusUseCase,
        DeleteReservationStatusUseCase,
        GetAllReservationStatusUseCase,
        FindByNameReservationStatusUseCase {
    private final ReservationStatusRepositoryPort reservationStatusRepositoryPort;

    public ReservationStatusService(ReservationStatusRepositoryPort reservationStatusRepositoryPort) {
        this.reservationStatusRepositoryPort = reservationStatusRepositoryPort;
    }

    @Override
    public ReservationStatusModel save(ReservationStatusModel reservationStatusModel) {
        if (reservationStatusRepositoryPort.existsByName(reservationStatusModel.getName())) {
            throw new IllegalArgumentException("Reservation status with name " + reservationStatusModel.getName() + " already exists");
        }
        return reservationStatusRepositoryPort.save(reservationStatusModel);
    }

    @Override
    public ReservationStatusModel findById(Integer id) {
        return reservationStatusRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation status with id " + id + " not found"));
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        reservationStatusRepositoryPort.deleteById(id);
    }

    @Override
    public ReservationStatusModel findByName(String name) {
        return reservationStatusRepositoryPort.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Reservation status with name " + name + " not found"));
    }

    @Override
    public List<ReservationStatusModel> findAll(int page, int size) {
        return reservationStatusRepositoryPort.findAll(page, size);
    }
}

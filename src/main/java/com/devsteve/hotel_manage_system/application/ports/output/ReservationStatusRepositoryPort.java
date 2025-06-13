package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;

import java.util.List;
import java.util.Optional;

public interface ReservationStatusRepositoryPort {
    ReservationStatusModel save(ReservationStatusModel model);
    Optional<ReservationStatusModel> findById(Integer id);
    void deleteById(Integer id);
    Optional<ReservationStatusModel> findByName(String name);
    boolean existsByName(String name);
    List<ReservationStatusModel> findAll(int page, int size);
}

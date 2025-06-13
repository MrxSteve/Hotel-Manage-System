package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationHistoryJpaRepository extends JpaRepository<ReservationHistory, Integer> {
    List<ReservationHistory> findByReservation_Id(UUID reservationId);
}

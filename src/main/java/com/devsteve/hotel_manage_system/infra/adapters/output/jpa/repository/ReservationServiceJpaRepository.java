package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.ReservationService;
import com.devsteve.hotel_manage_system.infra.entities.ReservationServiceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationServiceJpaRepository extends JpaRepository<ReservationService, ReservationServiceId> {
    List<ReservationService> findByReservation_Id(UUID reservationId);
    void deleteByReservation_Id(UUID reservationId);
}

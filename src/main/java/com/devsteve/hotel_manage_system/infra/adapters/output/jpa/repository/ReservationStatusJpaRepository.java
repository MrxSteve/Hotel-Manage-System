package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationStatusJpaRepository extends JpaRepository<ReservationStatus, Integer> {
    boolean existsByName(String name);
    Optional<ReservationStatus> findByName(String name);
    Optional<ReservationStatus> findById(Integer id);
    Page<ReservationStatus> findAll(Pageable pageable);
}

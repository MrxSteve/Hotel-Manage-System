package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.RoomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomStatusJpaRepository extends JpaRepository<RoomStatus, Integer> {
    boolean existsByName(String name);
    Optional<RoomStatus> findByName(String name);
    Optional<RoomStatus> findById(Integer id);
    Page<RoomStatus> findAll(Pageable pageable);
}

package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface RoomJpaRepository extends JpaRepository<Room, UUID>,
        JpaSpecificationExecutor<Room> {
    Optional<Room> findById(UUID id);
    Page<Room> findAll(Pageable pageable);
}

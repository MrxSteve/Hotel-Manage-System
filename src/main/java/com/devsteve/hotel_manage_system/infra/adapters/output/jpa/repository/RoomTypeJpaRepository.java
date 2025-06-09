package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeJpaRepository extends JpaRepository<RoomType, Integer> {
    boolean existsByNombre(String nombre);
    Optional<RoomType> findByNombreContainingIgnoreCase(String nombre);
    Optional<RoomType> findById(Integer id);
    Page<RoomType> findAll(Pageable pageable);
}

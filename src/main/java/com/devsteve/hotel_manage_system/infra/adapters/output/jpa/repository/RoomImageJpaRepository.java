package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomImageJpaRepository extends JpaRepository<RoomImage, Integer> {
    List<RoomImage> findByRoom_Id(UUID roomId);
    Optional<RoomImage> findById(Integer id);
    Optional<RoomImage> findByIdAndRoom_Id(Integer id, UUID roomId);
    void deleteByIdAndRoom_Id(Integer id, UUID roomId);
    void deleteAllByRoom_Id(UUID roomId);
}

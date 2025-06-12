package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.RoomPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomPriceJpaRepository extends JpaRepository<RoomPrice, Integer> {
    Page<RoomPrice> findAll(Pageable pageable);
    Optional<RoomPrice> findById(Integer id);
    List<RoomPrice> findByRoomType_Id(Integer roomTypeId);
}

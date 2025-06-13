package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.RoomPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomPriceJpaRepository extends JpaRepository<RoomPrice, Integer> {
    Page<RoomPrice> findAll(Pageable pageable);
    Optional<RoomPrice> findById(Integer id);
    List<RoomPrice> findByRoomType_Id(Integer roomTypeId);
    @Query("""
    SELECT rp FROM RoomPrice rp
    WHERE rp.roomType.id = :roomTypeId
      AND CURRENT_DATE BETWEEN rp.vigenteDesde AND COALESCE(rp.vigenteHasta, CURRENT_DATE)
    ORDER BY rp.vigenteDesde DESC
""")
    Optional<RoomPrice> findActiveByRoomTypeId(@Param("roomTypeId") Integer roomTypeId);

}

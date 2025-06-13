package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationJpaRepository extends JpaRepository<Reservation, UUID>,
        JpaSpecificationExecutor<Reservation> {
    Optional<Reservation> findById(UUID id);
    Page<Reservation> findAll(Pageable pageable);
    List<Reservation> findByUser_Id(UUID userId);

    @Query("""
    SELECT r FROM Reservation r
    WHERE r.room.id = :roomId
      AND r.status.name IN ('PENDIENTE', 'CONFIRMADA', 'COMPLETADA') 
      AND (
           (r.fechaInicio <= :fechaFin AND r.fechaFin >= :fechaInicio)
      )
""")
    List<Reservation> findOverlappingReservations(
            @Param("roomId") UUID roomId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

}

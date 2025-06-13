package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepositoryPort {
    ReservationModel save(ReservationModel reservation);
    List<ReservationModel> findAll(int page, int size);
    Optional<ReservationModel> findById(UUID id);
    void delete(UUID id);

    List<ReservationModel> search(
            Optional<UUID> userId,
            Optional<UUID> roomId,
            Optional<LocalDate> fechaInicio,
            Optional<LocalDate> fechaFin,
            Optional<Integer> statusId,
            Optional<BigDecimal> totalPagoDesde,
            Optional<BigDecimal> totalPagoHasta,
            Optional<Instant> createdAtDesde,
            Optional<Instant> createdAtHasta,
            int page, int size
    );
}

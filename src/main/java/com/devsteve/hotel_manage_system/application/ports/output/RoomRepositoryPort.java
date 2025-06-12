package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomRepositoryPort {
    RoomModel save(RoomModel roomModel);
    Optional<RoomModel> findById(UUID id);
    List<RoomModel> findAll(int page, int size);
    void delete(UUID id);

    List<RoomModel> findByFilters(
            Optional<Integer> numeroHabitacion,
            Optional<Integer> capacidad,
            Optional<Integer> roomTypeId,
            Optional<Integer> statusId,
            Optional<BigDecimal> precioMaximo,
            Optional<LocalDate> fechaReferencia,
            int page,
            int size);

}

package com.devsteve.hotel_manage_system.application.ports.input.room.rooms;

import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GetRoomsByFilterUseCase {
     List<RoomModel> getRoomsByFilter(
            Optional<Integer> numeroHabitacion,
            Optional<Integer> capacidad,
            Optional<Integer> roomTypeId,
            Optional<Integer> statusId,
            Optional<BigDecimal> precioMaximo,
            Optional<LocalDate> fechaReferencia,
            int page,
            int size);
}

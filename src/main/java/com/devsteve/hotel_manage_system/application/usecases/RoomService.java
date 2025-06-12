package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.room.rooms.*;
import com.devsteve.hotel_manage_system.application.ports.output.ImageStoragePort;
import com.devsteve.hotel_manage_system.application.ports.output.RoomImageRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RoomRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoomService implements
        CreateRoomUseCase,
        UpdateRoomUseCase,
        GetRoomByIdUseCase,
        DeleteRoomUseCase,
        ListRoomsUseCase,
        GetRoomsByFilterUseCase {
    private RoomRepositoryPort roomRepositoryPort;
    private final RoomImageRepositoryPort roomImageRepositoryPort;
    private final ImageStoragePort imageStoragePort;

    public RoomService(RoomImageRepositoryPort roomImageRepositoryPort, ImageStoragePort imageStoragePort, RoomRepositoryPort roomRepositoryPort) {
        this.roomImageRepositoryPort = roomImageRepositoryPort;
        this.imageStoragePort = imageStoragePort;
        this.roomRepositoryPort = roomRepositoryPort;
    }

    @Override
    public RoomModel create(RoomModel roomModel) {
        return roomRepositoryPort.save(roomModel);
    }

    @Override
    public RoomModel findById(UUID id) {
        return roomRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public List<RoomModel> findAll(int page, int size) {
        return roomRepositoryPort.findAll(page, size);
    }

    @Override
    public void delete(UUID id) {
        this.findById(id);
        roomRepositoryPort.delete(id);
        roomImageRepositoryPort.deleteAllByRoomId(id);
    }

    @Override
    public RoomModel update(UUID id, RoomModel roomModel) {
        RoomModel existing = this.findById(id);

        existing.setId(id);

        if (roomModel.getNumeroHabitacion() != null) {
            existing.setNumeroHabitacion(roomModel.getNumeroHabitacion());
        }

        if (roomModel.getCapacidad() != null) {
            existing.setCapacidad(roomModel.getCapacidad());
        }

        if (roomModel.getDescripcion() != null) {
            existing.setDescripcion(roomModel.getDescripcion());
        }

        if (roomModel.getRoomType() != null) {
            existing.setRoomType(roomModel.getRoomType());
        }

        if (roomModel.getStatus() != null) {
            existing.setStatus(roomModel.getStatus());
        }

        return roomRepositoryPort.save(existing);
    }

    @Override
    public List<RoomModel> getRoomsByFilter(
            Optional<Integer> numeroHabitacion,
            Optional<Integer> capacidad,
            Optional<Integer> roomTypeId,
            Optional<Integer> statusId,
            Optional<BigDecimal> precioMaximo,
            Optional<LocalDate> fechaReferencia,
            int page,
            int size) {
        return roomRepositoryPort.findByFilters(
                numeroHabitacion,
                capacidad,
                roomTypeId,
                statusId,
                precioMaximo,
                fechaReferencia,
                page,
                size
        );
    }
}

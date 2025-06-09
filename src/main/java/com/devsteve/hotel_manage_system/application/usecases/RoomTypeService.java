package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.room.type.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomTypeRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;

import java.util.List;

public class RoomTypeService implements
        CreateRoomTypeUseCase,
        FindByIdRoomTypeUseCase,
        DeleteRoomTypeUseCase,
        FindByNameRoomTypeUseCase,
        GetAllRoomTypeUseCase,
        UpdateRoomTypeUseCase {
    private final RoomTypeRepositoryPort roomTypeRepositoryPort;

    public RoomTypeService(RoomTypeRepositoryPort roomTypeRepositoryPort) {
        this.roomTypeRepositoryPort = roomTypeRepositoryPort;
    }

    @Override
    public RoomTypeModel save(RoomTypeModel roomTypeModel) {
        if (roomTypeRepositoryPort.existsByNombre(roomTypeModel.getNombre())) {
            throw new IllegalArgumentException("Room type with name " + roomTypeModel.getNombre() + " already exists");
        }

        return roomTypeRepositoryPort.save(roomTypeModel);
    }

    @Override
    public RoomTypeModel findById(Integer id) {
        return roomTypeRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room type with id " + id + " not found"));
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        roomTypeRepositoryPort.deleteById(id);
    }

    @Override
    public RoomTypeModel findByName(String name) {
        return roomTypeRepositoryPort.findByNombre(name)
                .orElseThrow(() -> new IllegalArgumentException("Room type with name " + name + " not found"));
    }

    @Override
    public List<RoomTypeModel> findAll(int page, int size) {
        return roomTypeRepositoryPort.findAll(page, size);
    }

    @Override
    public RoomTypeModel update(Integer id, RoomTypeModel roomTypeModel) {
        RoomTypeModel existingRoomType = this.findById(id);
        if (roomTypeModel.getNombre() != null &&
            roomTypeRepositoryPort.existsByNombre(roomTypeModel.getNombre()) &&
            !existingRoomType.getNombre().equals(roomTypeModel.getNombre())) {
            throw new IllegalArgumentException("Room type with name " + roomTypeModel.getNombre() + " already exists");
        }

        if (roomTypeModel.getNombre() != null) {
            existingRoomType.setNombre(roomTypeModel.getNombre());
        }

        if (roomTypeModel.getDescripcion() != null) {
            existingRoomType.setDescripcion(roomTypeModel.getDescripcion());
        }

        return roomTypeRepositoryPort.save(existingRoomType);
    }
}

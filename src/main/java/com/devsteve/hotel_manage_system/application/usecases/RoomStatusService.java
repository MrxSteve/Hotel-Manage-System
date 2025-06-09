package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.room.status.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomStatusRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;

import java.util.List;

public class RoomStatusService implements
        CreateRoomStatusUseCase,
        FindByIdRoomStatusUseCase,
        DeleteRoomStatusUseCase,
        GetAllRoomStatusUseCase,
        FindByNameRoomStatusUseCase {
    private final RoomStatusRepositoryPort roomStatusRepositoryPort;

    public RoomStatusService(RoomStatusRepositoryPort roomStatusRepositoryPort) {
        this.roomStatusRepositoryPort = roomStatusRepositoryPort;
    }

    @Override
    public RoomStatusModel create(RoomStatusModel roomStatusModel) {
        if (roomStatusRepositoryPort.existsByName(roomStatusModel.getName())) {
            throw new IllegalArgumentException("Room status with name " + roomStatusModel.getName() + " already exists");
        }
        return roomStatusRepositoryPort.save(roomStatusModel);
    }

    @Override
    public RoomStatusModel findById(Integer id) {
        return roomStatusRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room status with id " + id + " not found"));
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        roomStatusRepositoryPort.deleteById(id);
    }

    @Override
    public RoomStatusModel findByName(String name) {
        return roomStatusRepositoryPort.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Room status with name " + name + " not found"));
    }

    @Override
    public List<RoomStatusModel> findAll(int page, int size) {
        return roomStatusRepositoryPort.findAll(page, size);
    }
}

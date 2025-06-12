package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.room.price.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomPriceRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;

import java.util.List;

public class RoomPriceService implements
        CreateRoomPriceUseCase,
        GetAllRoomPricesUseCase,
        FindRoomPriceByIdUseCase,
        DeleteRoomPriceUseCase,
        FindRoomPricesByRoomTypeIdUseCase {
    private final RoomPriceRepositoryPort roomPriceRepositoryPort;

    public RoomPriceService(RoomPriceRepositoryPort roomPriceRepositoryPort) {
        this.roomPriceRepositoryPort = roomPriceRepositoryPort;
    }

    @Override
    public RoomPriceModel save(RoomPriceModel model) {
        return roomPriceRepositoryPort.save(model);
    }

    @Override
    public List<RoomPriceModel> findAll(int page, int size) {
        return roomPriceRepositoryPort.findAll(page, size);
    }

    @Override
    public RoomPriceModel findById(Integer id) {
        return roomPriceRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room price not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        roomPriceRepositoryPort.deleteById(id);
    }

    @Override
    public List<RoomPriceModel> findByRoomTypeId(Integer roomTypeId) {
        return roomPriceRepositoryPort.findByRoomTypeId(roomTypeId);
    }
}

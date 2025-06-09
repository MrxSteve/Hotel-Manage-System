package com.devsteve.hotel_manage_system.application.ports.input.room.type;

import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;

import java.util.List;

public interface GetAllRoomTypeUseCase {
    List<RoomTypeModel> findAll(int page, int size);
}

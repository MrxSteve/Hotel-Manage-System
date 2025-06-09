package com.devsteve.hotel_manage_system.application.ports.input.room.type;

import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;

public interface UpdateRoomTypeUseCase {
    RoomTypeModel update(Integer id, RoomTypeModel roomTypeModel);
}

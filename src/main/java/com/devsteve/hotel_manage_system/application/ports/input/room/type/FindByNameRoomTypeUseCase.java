package com.devsteve.hotel_manage_system.application.ports.input.room.type;

import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;

public interface FindByNameRoomTypeUseCase {
    RoomTypeModel findByName(String name);
}

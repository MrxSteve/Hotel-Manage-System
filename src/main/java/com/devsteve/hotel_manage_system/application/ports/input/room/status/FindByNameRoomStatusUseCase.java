package com.devsteve.hotel_manage_system.application.ports.input.room.status;

import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;

public interface FindByNameRoomStatusUseCase {
    RoomStatusModel findByName(String name);
}

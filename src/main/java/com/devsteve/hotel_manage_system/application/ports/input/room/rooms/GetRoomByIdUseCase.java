package com.devsteve.hotel_manage_system.application.ports.input.room.rooms;

import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;

import java.util.UUID;

public interface GetRoomByIdUseCase {
    RoomModel findById(UUID id);
}

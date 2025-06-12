package com.devsteve.hotel_manage_system.application.ports.input.room.rooms;

import java.util.UUID;

public interface DeleteRoomUseCase {
    void delete(UUID id);
}

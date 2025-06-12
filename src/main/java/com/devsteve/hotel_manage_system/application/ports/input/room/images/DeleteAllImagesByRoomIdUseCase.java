package com.devsteve.hotel_manage_system.application.ports.input.room.images;

import java.util.UUID;

public interface DeleteAllImagesByRoomIdUseCase {
    void deleteAllImagesByRoomId(UUID roomId);
}

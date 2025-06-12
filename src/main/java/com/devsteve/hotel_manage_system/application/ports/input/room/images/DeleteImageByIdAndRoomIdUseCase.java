package com.devsteve.hotel_manage_system.application.ports.input.room.images;

import java.util.UUID;

public interface DeleteImageByIdAndRoomIdUseCase {
    void deleteImageByIdAndRoomId(Integer imageId, UUID roomId);
}

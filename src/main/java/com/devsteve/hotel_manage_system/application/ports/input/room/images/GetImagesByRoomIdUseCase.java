package com.devsteve.hotel_manage_system.application.ports.input.room.images;

import com.devsteve.hotel_manage_system.domain.models.room.RoomImageModel;

import java.util.List;
import java.util.UUID;

public interface GetImagesByRoomIdUseCase {
    List<RoomImageModel> getImagesByRoomId(UUID roomId);
}

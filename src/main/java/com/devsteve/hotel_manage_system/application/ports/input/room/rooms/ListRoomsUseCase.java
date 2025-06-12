package com.devsteve.hotel_manage_system.application.ports.input.room.rooms;

import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;

import java.util.List;

public interface ListRoomsUseCase {
    List<RoomModel> findAll(int page, int size);
}

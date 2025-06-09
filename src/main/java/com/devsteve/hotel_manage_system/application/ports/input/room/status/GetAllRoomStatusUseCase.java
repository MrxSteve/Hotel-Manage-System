package com.devsteve.hotel_manage_system.application.ports.input.room.status;

import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;

import java.util.List;

public interface GetAllRoomStatusUseCase {
    List<RoomStatusModel> findAll(int page, int size);
}

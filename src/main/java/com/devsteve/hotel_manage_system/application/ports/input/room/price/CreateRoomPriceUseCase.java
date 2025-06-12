package com.devsteve.hotel_manage_system.application.ports.input.room.price;

import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;

public interface CreateRoomPriceUseCase {
    RoomPriceModel save(RoomPriceModel model);
}

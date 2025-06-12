package com.devsteve.hotel_manage_system.application.ports.input.room.price;

import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;

import java.util.List;

public interface GetAllRoomPricesUseCase {
    List<RoomPriceModel> findAll(int page, int size);
}

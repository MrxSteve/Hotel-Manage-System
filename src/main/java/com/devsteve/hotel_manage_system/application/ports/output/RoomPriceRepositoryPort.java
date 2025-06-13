package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomPriceRepositoryPort {
    RoomPriceModel save(RoomPriceModel model);
    List<RoomPriceModel> findAll(int page, int size);
    Optional<RoomPriceModel> findById(Integer id);
    void deleteById(Integer id);
    List<RoomPriceModel> findByRoomTypeId(Integer roomTypeId);
    Optional<RoomPriceModel> findActivePriceByRoomId(UUID roomId);
}

package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.room.RoomImageModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomImageRepositoryPort {
    RoomImageModel save(RoomImageModel image);
    List<RoomImageModel> findByRoomId(UUID roomId);
    Optional<RoomImageModel> findById(Integer id);
    Optional<RoomImageModel> findByIdAndRoomId(Integer id, UUID roomId);
    void deleteById(Integer id);
    void deleteByIdAndRoomId(Integer id, UUID roomId);
    void deleteAllByRoomId(UUID roomId);
}

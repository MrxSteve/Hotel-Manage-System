package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;

import java.util.List;
import java.util.Optional;

public interface RoomStatusRepositoryPort {
    RoomStatusModel save(RoomStatusModel model);
    Optional<RoomStatusModel> findById(Integer id);
    void deleteById(Integer id);
    Optional<RoomStatusModel> findByName(String name);
    List<RoomStatusModel> findAll(int page, int size);
    boolean existsByName(String name);
}

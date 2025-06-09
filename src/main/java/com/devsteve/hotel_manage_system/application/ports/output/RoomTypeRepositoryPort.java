package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;

import java.util.List;
import java.util.Optional;

public interface RoomTypeRepositoryPort {
    RoomTypeModel save(RoomTypeModel model);
    Optional<RoomTypeModel> findById(Integer id);
    void deleteById(Integer id);
    Optional<RoomTypeModel> findByNombre(String name);
    List<RoomTypeModel> findAll(int page, int size);
    boolean existsByNombre(String name);
}

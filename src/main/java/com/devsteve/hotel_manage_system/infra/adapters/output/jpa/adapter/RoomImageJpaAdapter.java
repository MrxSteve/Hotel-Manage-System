package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RoomImageRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomImageModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomImageJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.RoomImage;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class RoomImageJpaAdapter implements RoomImageRepositoryPort {
    private final RoomImageJpaRepository roomImageJpaRepository;
    private final RoomImageMapper roomImageMapper;

    @Override
    public RoomImageModel save(RoomImageModel image) {
        RoomImage entity = roomImageMapper.modelToEntity(image);
        RoomImage savedEntity = roomImageJpaRepository.save(entity);

        return roomImageMapper.entityToModel(savedEntity);
    }

    @Override
    public List<RoomImageModel> findByRoomId(UUID roomId) {
        return roomImageMapper.toModelList(roomImageJpaRepository.findByRoom_Id(roomId));
    }

    @Override
    public Optional<RoomImageModel> findById(Integer id) {
        return roomImageJpaRepository.findById(id)
                .map(roomImageMapper::entityToModel);
    }

    @Override
    public Optional<RoomImageModel> findByIdAndRoomId(Integer id, UUID roomId) {
        return roomImageJpaRepository.findByIdAndRoom_Id(id, roomId)
                .map(roomImageMapper::entityToModel);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);
        roomImageJpaRepository.deleteById(id);
    }

    @Override
    public void deleteByIdAndRoomId(Integer id, UUID roomId) {
        Optional<RoomImageModel> image = this.findByIdAndRoomId(id, roomId);
        if (image.isPresent()) {
            roomImageJpaRepository.deleteByIdAndRoom_Id(id, roomId);
        } else {
            throw new RuntimeException("Image not found with ID: " + id + " and Room ID: " + roomId);
        }
    }

    @Override
    public void deleteAllByRoomId(UUID roomId) {
        List<RoomImageModel> images = this.findByRoomId(roomId);
        if (images.isEmpty()) {
            throw new RuntimeException("No images found for Room ID: " + roomId);
        }
        roomImageJpaRepository.deleteAllByRoom_Id(roomId);
    }
}

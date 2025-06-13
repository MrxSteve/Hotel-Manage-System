package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RoomPriceRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomPriceJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomTypeJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.Room;
import com.devsteve.hotel_manage_system.infra.entities.RoomPrice;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomPriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoomPriceJpaAdapter implements RoomPriceRepositoryPort {
    private final RoomPriceJpaRepository roomPriceJpaRepository;
    private final RoomPriceMapper roomPriceMapper;
    private final RoomTypeJpaRepository roomTypeJpaRepository;
    private final RoomJpaRepository roomJpaRepository;

    @Override
    public RoomPriceModel save(RoomPriceModel model) {
        RoomPrice entity = roomPriceMapper.modelToEntity(model);

        Integer roomTypeId = model.getRoomType().getId();
        entity.setRoomType(roomTypeJpaRepository.findById(roomTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Room type with ID " + roomTypeId + " not found.")));

        RoomPrice saved = roomPriceJpaRepository.save(entity);

        return roomPriceMapper.entityToModel(saved);
    }

    @Override
    public List<RoomPriceModel> findAll(int page, int size) {
        return roomPriceJpaRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(roomPriceMapper::entityToModel)
                .toList();
    }

    @Override
    public Optional<RoomPriceModel> findById(Integer id) {
        return roomPriceJpaRepository.findById(id)
                .map(roomPriceMapper::entityToModel);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);
        roomPriceJpaRepository.deleteById(id);
    }

    @Override
    public List<RoomPriceModel> findByRoomTypeId(Integer roomTypeId) {
        return roomPriceJpaRepository.findByRoomType_Id(roomTypeId)
                .stream()
                .map(roomPriceMapper::entityToModel)
                .toList();
    }

    @Override
    public Optional<RoomPriceModel> findActivePriceByRoomId(UUID roomId) {
        return roomJpaRepository.findById(roomId)
                .map(Room::getRoomType)
                .map(roomType -> roomType.getId())
                .flatMap(roomPriceJpaRepository::findActiveByRoomTypeId)
                .map(roomPriceMapper::entityToModel);
    }
}

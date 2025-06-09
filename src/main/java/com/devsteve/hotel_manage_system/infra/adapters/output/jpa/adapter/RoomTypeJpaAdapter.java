package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RoomTypeRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomTypeJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.RoomType;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoomTypeJpaAdapter implements RoomTypeRepositoryPort {
    private final RoomTypeJpaRepository roomTypeJpaRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public RoomTypeModel save(RoomTypeModel model) {
        RoomType entity = roomTypeMapper.modelToEntity(model);
        RoomType savedEntity = roomTypeJpaRepository.save(entity);

        return roomTypeMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<RoomTypeModel> findById(Integer id) {
        return roomTypeJpaRepository.findById(id)
                .map(roomTypeMapper::entityToModel);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);
        roomTypeJpaRepository.deleteById(id);
    }

    @Override
    public Optional<RoomTypeModel> findByNombre(String name) {
        return roomTypeJpaRepository.findByNombreContainingIgnoreCase(name)
                .map(roomTypeMapper::entityToModel);
    }

    @Override
    public List<RoomTypeModel> findAll(int page, int size) {
        return roomTypeJpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(roomTypeMapper::entityToModel)
                .toList();
    }

    @Override
    public boolean existsByNombre(String name) {
        return roomTypeJpaRepository.existsByNombre(name);
    }
}

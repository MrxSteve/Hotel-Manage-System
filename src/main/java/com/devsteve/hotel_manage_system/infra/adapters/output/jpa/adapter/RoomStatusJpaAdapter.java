package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RoomStatusRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomStatusJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.RoomStatus;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoomStatusJpaAdapter implements RoomStatusRepositoryPort {
    private final RoomStatusJpaRepository roomStatusJpaRepository;
    private final RoomStatusMapper roomStatusMapper;

    @Override
    public RoomStatusModel save(RoomStatusModel model) {
        RoomStatus entity = roomStatusMapper.modelToEntity(model);
        RoomStatus savedEntity = roomStatusJpaRepository.save(entity);

        return roomStatusMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<RoomStatusModel> findById(Integer id) {
        return roomStatusJpaRepository.findById(id)
                .map(roomStatusMapper::entityToModel);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);
        roomStatusJpaRepository.deleteById(id);
    }

    @Override
    public Optional<RoomStatusModel> findByName(String name) {
        return roomStatusJpaRepository.findByName(name)
                .map(roomStatusMapper::entityToModel);
    }

    @Override
    public List<RoomStatusModel> findAll(int page, int size) {
        return roomStatusJpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(roomStatusMapper::entityToModel)
                .toList();
    }

    @Override
    public boolean existsByName(String name) {
        return roomStatusJpaRepository.existsByName(name);
    }
}

package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RoomRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomPriceJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomStatusJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomTypeJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.specification.RoomSpecification;
import com.devsteve.hotel_manage_system.infra.entities.Room;
import com.devsteve.hotel_manage_system.infra.entities.RoomPrice;
import com.devsteve.hotel_manage_system.infra.entities.RoomStatus;
import com.devsteve.hotel_manage_system.infra.entities.RoomType;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomMapper;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomPriceMapper;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomStatusMapper;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoomJpaAdapter implements RoomRepositoryPort {
    private final RoomJpaRepository roomJpaRepository;
    private final RoomMapper roomMapper;
    private final RoomTypeMapper roomTypeMapper;
    private final RoomStatusMapper roomStatusMapper;
    private final RoomPriceMapper roomPriceMapper;
    private final RoomPriceJpaRepository roomPriceJpaRepository;
    private final RoomTypeJpaRepository roomTypeJpaRepository;
    private final RoomStatusJpaRepository roomStatusJpaRepository;

    @Override
    public RoomModel save(RoomModel model) {
        Room entity;

        if (model.getId() == null) {
            entity = roomMapper.modelToEntity(model);
        } else {
            entity = roomJpaRepository.findById(model.getId())
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + model.getId()));

            entity.setNumeroHabitacion(model.getNumeroHabitacion());
            entity.setCapacidad(model.getCapacidad());
            entity.setDescripcion(model.getDescripcion());
        }

        if (model.getRoomType() != null && model.getRoomType().getId() != null) {
            RoomType roomType = roomTypeJpaRepository.findById(model.getRoomType().getId())
                    .orElseThrow(() -> new RuntimeException("RoomType not found with ID: " + model.getRoomType().getId()));
            entity.setRoomType(roomType);
        }

        if (model.getStatus() != null && model.getStatus().getId() != null) {
            RoomStatus status = roomStatusJpaRepository.findById(model.getStatus().getId())
                    .orElseThrow(() -> new RuntimeException("RoomStatus not found with ID: " + model.getStatus().getId()));
            entity.setStatus(status);
        }

        Room saved = roomJpaRepository.save(entity);
        return enrichModel(saved);
    }

    @Override
    public Optional<RoomModel> findById(UUID id) {
        return roomJpaRepository.findById(id)
                .map(this::enrichModel);
    }

    @Override
    public List<RoomModel> findAll(int page, int size) {
        return roomJpaRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(this::enrichModel)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        this.findById(id);
        roomJpaRepository.deleteById(id);
    }

    @Override
    public List<RoomModel> findByFilters(
            Optional<Integer> numeroHabitacion,
            Optional<Integer> capacidad,
            Optional<Integer> roomTypeId,
            Optional<Integer> statusId,
            Optional<BigDecimal> precioMaximo,
            Optional<LocalDate> fechaReferencia,
            int page,
            int size) {

        Specification<Room> spec = Specification
                .where(RoomSpecification.hasNumeroHabitacion(numeroHabitacion.orElse(null)))
                .and(RoomSpecification.hasCapacidad(capacidad.orElse(null)))
                .and(RoomSpecification.hasRoomTypeId(roomTypeId.orElse(null)))
                .and(RoomSpecification.hasStatusId(statusId.orElse(null)))
                .and(RoomSpecification.hasPrecioMenorOIgual(precioMaximo.orElse(null), fechaReferencia.orElse(LocalDate.now())));

        return roomJpaRepository.findAll(spec, PageRequest.of(page, size))
                .stream()
                .map(this::enrichModel)
                .toList();
    }

    private RoomModel enrichModel(Room entity) {
        RoomModel model = roomMapper.entityToModel(entity);

        if (entity.getRoomType() != null) {
            RoomType roomType = entity.getRoomType();
            model.setRoomType(roomTypeMapper.entityToModel(roomType));

            List<RoomPrice> precios = roomPriceJpaRepository.findByRoomType_Id(roomType.getId());
            model.setPrecios(precios.stream()
                    .map(roomPriceMapper::entityToModel)
                    .toList());
        }

        if (entity.getStatus() != null) {
            model.setStatus(roomStatusMapper.entityToModel(entity.getStatus()));
        }

        return model;
    }
}

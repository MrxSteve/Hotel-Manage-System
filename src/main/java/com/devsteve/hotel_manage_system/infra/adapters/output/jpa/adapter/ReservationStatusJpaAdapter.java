package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.ReservationStatusRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.ReservationStatusJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.ReservationStatus;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReservationStatusJpaAdapter implements ReservationStatusRepositoryPort {
    private final ReservationStatusJpaRepository reservationStatusJpaRepository;
    private final ReservationStatusMapper reservationStatusMapper;

    @Override
    public ReservationStatusModel save(ReservationStatusModel model) {
        ReservationStatus entity = reservationStatusMapper.modelToEntity(model);
        ReservationStatus savedEntity = reservationStatusJpaRepository.save(entity);

        return reservationStatusMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<ReservationStatusModel> findById(Integer id) {
        return reservationStatusJpaRepository.findById(id)
                .map(reservationStatusMapper::entityToModel);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);
        reservationStatusJpaRepository.deleteById(id);
    }

    @Override
    public Optional<ReservationStatusModel> findByName(String name) {
        return reservationStatusJpaRepository.findByName(name)
                .map(reservationStatusMapper::entityToModel);
    }

    @Override
    public boolean existsByName(String name) {
        return reservationStatusJpaRepository.existsByName(name);
    }

    @Override
    public List<ReservationStatusModel> findAll(int page, int size) {
        return reservationStatusJpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(reservationStatusMapper::entityToModel)
                .toList();
    }
}

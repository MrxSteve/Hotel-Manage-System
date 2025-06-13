package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.ReservationHistoryRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.ReservationHistoryJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.ReservationHistory;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReservationHistoryJpaAdapter implements ReservationHistoryRepositoryPort {
    private final ReservationHistoryJpaRepository reservationHistoryJpaRepository;
    private final ReservationHistoryMapper reservationHistoryMapper;

    @Override
    public ReservationHistoryModel save(ReservationHistoryModel reservationHistory) {
        ReservationHistory entity = reservationHistoryMapper.modelToEntity(reservationHistory);
        ReservationHistory savedEntity = reservationHistoryJpaRepository.save(entity);

        return reservationHistoryMapper.entityToModel(savedEntity);
    }

    @Override
    public List<ReservationHistoryModel> findByReservationId(UUID reservationId) {
        return reservationHistoryJpaRepository.findByReservation_Id(reservationId)
                .stream()
                .map(reservationHistoryMapper::entityToModel)
                .toList();
    }
}

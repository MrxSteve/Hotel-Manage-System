package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.ReservationRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.ReservationJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.ReservationStatusJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.specification.ReservationSpecification;
import com.devsteve.hotel_manage_system.infra.entities.Reservation;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationMapper;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationJpaAdapter implements ReservationRepositoryPort {
    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationStatusJpaRepository reservationStatusJpaRepository;

    private final ReservationMapper reservationMapper;
    private final ReservationStatusMapper reservationStatusMapper;

    @Override
    public ReservationModel save(ReservationModel model) {
        Reservation entity = reservationMapper.modelToEntity(model);
        Reservation savedEntity = reservationJpaRepository.save(entity);
        return enrichModel(savedEntity);
    }

    @Override
    public List<ReservationModel> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reservationJpaRepository.findAll(pageable)
                .stream()
                .map(this::enrichModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservationModel> findById(UUID id) {
        return reservationJpaRepository.findById(id)
                .map(this::enrichModel);
    }

    @Override
    public void delete(UUID id) {
        reservationJpaRepository.deleteById(id);
    }

    @Override
    public List<ReservationModel> search(
            Optional<UUID> userId,
            Optional<UUID> roomId,
            Optional<LocalDate> fechaInicio,
            Optional<LocalDate> fechaFin,
            Optional<Integer> statusId,
            Optional<BigDecimal> totalPagoDesde,
            Optional<BigDecimal> totalPagoHasta,
            Optional<Instant> createdAtDesde,
            Optional<Instant> createdAtHasta,
            int page, int size
    ) {
        Specification<Reservation> spec = Specification
                .where(ReservationSpecification.hasUserId(userId.orElse(null)))
                .and(ReservationSpecification.hasRoomId(roomId.orElse(null)))
                .and(ReservationSpecification.hasStatusId(statusId.orElse(null)))
                .and(ReservationSpecification.hasFechaInicioDesde(fechaInicio.orElse(null)))
                .and(ReservationSpecification.hasFechaFinHasta(fechaFin.orElse(null)))
                .and(ReservationSpecification.hasTotalPagoDesde(totalPagoDesde.orElse(null)))
                .and(ReservationSpecification.hasTotalPagoHasta(totalPagoHasta.orElse(null)))
                .and(ReservationSpecification.hasCreatedAtDesde(createdAtDesde.orElse(null)))
                .and(ReservationSpecification.hasCreatedAtHasta(createdAtHasta.orElse(null)));

        return reservationJpaRepository.findAll(spec, PageRequest.of(page, size))
                .stream()
                .map(this::enrichModel)
                .toList();
    }

    @Override
    public List<ReservationModel> findByUserId(UUID userId) {
        return reservationJpaRepository.findByUser_Id(userId)
                .stream()
                .map(this::enrichModel)
                .collect(Collectors.toList());
    }

    private ReservationModel enrichModel(Reservation entity) {
        ReservationModel model = reservationMapper.entityToModel(entity);

        if (entity.getStatus() != null) {
            model.setStatus(reservationStatusMapper.entityToModel(entity.getStatus()));
        }

        return model;
    }
}

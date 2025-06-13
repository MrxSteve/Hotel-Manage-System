package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.ReservationServiceRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationServiceModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.ExtraServiceJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.ReservationServiceJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.ReservationService;
import com.devsteve.hotel_manage_system.infra.entities.ReservationServiceId;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationServiceAdapter implements ReservationServiceRepositoryPort {
    private final ReservationServiceJpaRepository reservationServiceJpaRepository;
    private final ReservationServiceMapper reservationServiceMapper;
    private final ExtraServiceJpaRepository extraServiceJpaRepository;

    @Override
    public ReservationServiceModel save(ReservationServiceModel model) {
        ReservationService entity = reservationServiceMapper.modelToEntity(model);
        ReservationService saved = reservationServiceJpaRepository.save(entity);
        return reservationServiceMapper.entityToModel(saved);
    }

    @Override
    public List<ReservationServiceModel> findByReservationId(UUID reservationId) {
        return reservationServiceJpaRepository.findByReservation_Id(reservationId)
                .stream()
                .map(entity -> {
                    ReservationServiceModel model = reservationServiceMapper.entityToModel(entity);

                    // Enriquecemos con datos adicionales
                    extraServiceJpaRepository.findById(entity.getId().getServiceId()).ifPresent(service -> {
                        model.setServiceNombre(service.getNombre()); // <- este es el enriquecimiento
                    });

                    return model;
                })
                .toList();
    }

    @Override
    public void deleteByReservationId(UUID reservationId) {
        reservationServiceJpaRepository.deleteByReservation_Id(reservationId);
    }

    @Override
    public void deleteById(UUID reservationId, Integer serviceId) {
        ReservationServiceId id = new ReservationServiceId();
        id.setReservationId(reservationId);
        id.setServiceId(serviceId);
        reservationServiceJpaRepository.deleteById(id);
    }
}

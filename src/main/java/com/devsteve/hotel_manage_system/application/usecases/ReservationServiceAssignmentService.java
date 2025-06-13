package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.AssignReservationServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetReservationServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.RemoveReservationServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.ExtraServiceRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationServiceRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationServiceModel;

import java.util.List;
import java.util.UUID;

public class ReservationServiceAssignmentService implements
        AssignReservationServicesUseCase,
        GetReservationServicesUseCase,
        RemoveReservationServiceUseCase {

    private final ReservationServiceRepositoryPort reservationServiceRepositoryPort;
    private final ExtraServiceRepositoryPort extraServiceRepositoryPort;

    public ReservationServiceAssignmentService(
            ReservationServiceRepositoryPort reservationServiceRepositoryPort,
            ExtraServiceRepositoryPort extraServiceRepositoryPort
    ) {
        this.reservationServiceRepositoryPort = reservationServiceRepositoryPort;
        this.extraServiceRepositoryPort = extraServiceRepositoryPort;
    }

    @Override
    public void assign(UUID reservationId, List<ReservationServiceModel> services) {
        if (services == null || services.isEmpty()) {
            throw new IllegalArgumentException("Debe asignar al menos un servicio");
        }

        for (ReservationServiceModel service : services) {
            if (!reservationId.equals(service.getReservationId())) {
                throw new IllegalArgumentException("Todos los servicios deben pertenecer a la misma reservación");
            }

            ExtraServiceModel extra = extraServiceRepositoryPort.findById(service.getServiceId())
                    .orElseThrow(() -> new IllegalArgumentException("Servicio extra no encontrado"));

            service.setPrecioUnitario(extra.getPrecio());

            reservationServiceRepositoryPort.save(service);
        }
    }

    @Override
    public List<ReservationServiceModel> getByReservationId(UUID reservationId) {
        return reservationServiceRepositoryPort.findByReservationId(reservationId);
    }

    @Override
    public void remove(UUID reservationId, Integer serviceId) {
        reservationServiceRepositoryPort.deleteById(reservationId, serviceId);
    }
}

package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations.*;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationHistoryRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationStatusRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;
import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationService implements
        CreateReservationUseCase,
        GetAllReservationsUseCase,
        FindReservationByIdUseCase,
        DeleteReservationUseCase,
        SearchReservationsUseCase,
        ChangeReservationStatusUseCase {
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final ReservationStatusRepositoryPort reservationStatusRepositoryPort;
    private final ReservationHistoryRepositoryPort reservationHistoryRepositoryPort;

    public ReservationService(ReservationRepositoryPort reservationRepositoryPort,
                              ReservationStatusRepositoryPort reservationStatusRepositoryPort,
                              ReservationHistoryRepositoryPort reservationHistoryRepositoryPort) {
        this.reservationRepositoryPort = reservationRepositoryPort;
        this.reservationStatusRepositoryPort = reservationStatusRepositoryPort;
        this.reservationHistoryRepositoryPort = reservationHistoryRepositoryPort;
    }

    @Override
    public ReservationModel save(ReservationModel reservation) {
        if (reservation.getStatusId() == null) {
            throw new IllegalArgumentException("El campo statusId no puede ser nulo");
        }

        ReservationStatusModel status = reservationStatusRepositoryPort.findById(reservation.getStatusId())
                .orElseThrow(() -> new RuntimeException("Estado no válido"));

        reservation.setStatus(status);
        return reservationRepositoryPort.save(reservation);
    }

    @Override
    public List<ReservationModel> findAll(int page, int size) {
        return reservationRepositoryPort.findAll(page, size);
    }

    @Override
    public ReservationModel findById(UUID id) {
        return reservationRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
    }

    @Override
    public void deleteById(UUID id) {
        this.findById(id);
        reservationRepositoryPort.delete(id);
    }

    @Override
    public List<ReservationModel> search(Optional<UUID> userId, Optional<UUID> roomId,
                                         Optional<LocalDate> fechaInicio, Optional<LocalDate> fechaFin,
                                         Optional<Integer> statusId, Optional<BigDecimal> totalPagoDesde,
                                         Optional<BigDecimal> totalPagoHasta, Optional<Instant> createdAtDesde,
                                         Optional<Instant> createdAtHasta, int page, int size) {
        return reservationRepositoryPort.search(
                userId, roomId, fechaInicio, fechaFin, statusId,
                totalPagoDesde, totalPagoHasta,
                createdAtDesde, createdAtHasta, page, size
        );
    }

    @Override
    public ReservationModel changeStatus(UUID reservationId, Integer nuevoStatusId, @Nullable String comentario) {
        ReservationModel reserva = this.findById(reservationId);

        // Obtener el estado anterior desde el objeto `status`
        ReservationStatusModel estadoAnterior = reserva.getStatus();
        if (estadoAnterior == null || estadoAnterior.getId() == null) {
            throw new RuntimeException("Estado anterior no disponible");
        }

        // Obtener el nuevo estado completo
        ReservationStatusModel estadoNuevo = reservationStatusRepositoryPort.findById(nuevoStatusId)
                .orElseThrow(() -> new RuntimeException("Estado nuevo no encontrado"));

        // Actualizar estado de la reserva
        reserva.setStatus(estadoNuevo);

        ReservationModel actualizada = reservationRepositoryPort.save(reserva);

        // Registrar historial
        ReservationHistoryModel history = new ReservationHistoryModel();
        history.setReservationId(reservationId);
        history.setEstadoAnterior(estadoAnterior);
        history.setEstadoNuevo(estadoNuevo);
        history.setFechaCambio(Instant.now());
        history.setComentario(comentario);

        reservationHistoryRepositoryPort.save(history);

        return actualizada;
    }

}

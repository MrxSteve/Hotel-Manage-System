package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations.*;
import com.devsteve.hotel_manage_system.application.ports.output.*;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;
import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;
import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;
import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;
import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private final RoomRepositoryPort roomRepositoryPort;
    private final RoomPriceRepositoryPort roomPriceRepositoryPort;
    private final RoomStatusRepositoryPort roomStatusRepositoryPort;

    public ReservationService(
            ReservationRepositoryPort reservationRepositoryPort,
            ReservationStatusRepositoryPort reservationStatusRepositoryPort,
            ReservationHistoryRepositoryPort reservationHistoryRepositoryPort,
            RoomRepositoryPort roomRepositoryPort,
            RoomPriceRepositoryPort roomPriceRepositoryPort,
            RoomStatusRepositoryPort roomStatusRepositoryPort) {
        this.reservationRepositoryPort = reservationRepositoryPort;
        this.reservationStatusRepositoryPort = reservationStatusRepositoryPort;
        this.reservationHistoryRepositoryPort = reservationHistoryRepositoryPort;
        this.roomRepositoryPort = roomRepositoryPort;
        this.roomPriceRepositoryPort = roomPriceRepositoryPort;
        this.roomStatusRepositoryPort = roomStatusRepositoryPort;
    }

    @Override
    public ReservationModel save(ReservationModel reservation) {
        if (reservation.getUserId() == null) {
            throw new IllegalArgumentException("El campo userId no puede ser nulo");
        }

        if (reservation.getStatusId() == null) {
            throw new IllegalArgumentException("El campo statusId no puede ser nulo");
        }

        ReservationStatusModel status = reservationStatusRepositoryPort.findById(reservation.getStatusId())
                .orElseThrow(() -> new RuntimeException("Estado no válido"));

        long noches = ChronoUnit.DAYS.between(reservation.getFechaInicio(), reservation.getFechaFin());
        if (noches <= 0) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }

        List<ReservationModel> conflictos = reservationRepositoryPort.findOverlappingReservations(
                reservation.getRoomId(), reservation.getFechaInicio(), reservation.getFechaFin()
        );

        if (!conflictos.isEmpty()) {
            throw new RuntimeException("La habitación ya está reservada en las fechas seleccionadas");
        }

        if (reservation.getTotalPago() == null) {
            RoomModel room = roomRepositoryPort.findById(reservation.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

            RoomPriceModel precio = roomPriceRepositoryPort.findActivePriceByRoomId(room.getId())
                    .orElseThrow(() -> new RuntimeException("No hay precio activo para esta habitación"));

            BigDecimal total = precio.getPrecioPorNoche().multiply(BigDecimal.valueOf(noches));
            reservation.setTotalPago(total);
        }

        Integer statusOcupadaId = roomStatusRepositoryPort.findByName("OCUPADA")
                .orElseThrow(() -> new RuntimeException("Estado OCUPADA no encontrado")).getId();

        roomRepositoryPort.changeRoomStatus(reservation.getRoomId(), statusOcupadaId);

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

        ReservationStatusModel estadoAnterior = reserva.getStatus();
        if (estadoAnterior == null || estadoAnterior.getId() == null) {
            throw new RuntimeException("Estado anterior no disponible");
        }

        ReservationStatusModel estadoNuevo = reservationStatusRepositoryPort.findById(nuevoStatusId)
                .orElseThrow(() -> new RuntimeException("Estado nuevo no encontrado"));

        reserva.setStatus(estadoNuevo);
        ReservationModel actualizada = reservationRepositoryPort.save(reserva);

        ReservationHistoryModel history = new ReservationHistoryModel();
        history.setReservationId(reservationId);
        history.setEstadoAnterior(estadoAnterior);
        history.setEstadoNuevo(estadoNuevo);
        history.setFechaCambio(Instant.now());
        history.setComentario(comentario);
        reservationHistoryRepositoryPort.save(history);

        RoomStatusModel ocupada = roomStatusRepositoryPort.findByName("OCUPADA")
                .orElseThrow(() -> new RuntimeException("Estado OCUPADA no encontrado"));
        RoomStatusModel disponible = roomStatusRepositoryPort.findByName("DISPONIBLE")
                .orElseThrow(() -> new RuntimeException("Estado DISPONIBLE no encontrado"));

        if (estadoNuevo.getName().equalsIgnoreCase("CANCELADA")
                || estadoNuevo.getName().equalsIgnoreCase("CHECKOUT")) {
            roomRepositoryPort.changeRoomStatus(reserva.getRoomId(), disponible.getId());
        } else if (estadoNuevo.getName().equalsIgnoreCase("CHECKIN")) {
            roomRepositoryPort.changeRoomStatus(reserva.getRoomId(), ocupada.getId());
        }

        return actualizada;
    }

}

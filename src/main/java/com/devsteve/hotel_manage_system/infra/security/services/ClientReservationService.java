package com.devsteve.hotel_manage_system.infra.security.services;

import com.devsteve.hotel_manage_system.application.ports.output.*;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;
import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;
import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;
import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;
import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoomPriceJpaRepository;
import com.devsteve.hotel_manage_system.infra.security.dto.req.ReservationClientRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationRequest;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientReservationService {
    private final ReservationMapper reservationMapper;
    private final ReservationStatusRepositoryPort reservationStatusRepositoryPort;
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final CurrentUserService currentUserService;
    private final RoomPriceRepositoryPort roomPriceRepositoryPort;
    private final RoomRepositoryPort roomRepositoryPort;
    private final RoomStatusRepositoryPort roomStatusRepositoryPort;
    private final ReservationHistoryRepositoryPort reservationHistoryRepositoryPort;

    public ReservationModel createByClient(ReservationClientRequest request) {
        UserModel currentUser = currentUserService.getCurrentUser();

        RoomModel room = roomRepositoryPort.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        RoomPriceModel precio = roomPriceRepositoryPort.findActivePriceByRoomId(room.getId())
                .orElseThrow(() -> new RuntimeException("No hay precio activo para esta habitación"));

        ReservationStatusModel pendiente = reservationStatusRepositoryPort.findByName("PENDIENTE")
                .orElseThrow(() -> new RuntimeException("Estado PENDIENTE no encontrado"));

        long noches = ChronoUnit.DAYS.between(request.getFechaInicio(), request.getFechaFin());
        if (noches <= 0) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio");
        }

        List<ReservationModel> conflictos = reservationRepositoryPort.findOverlappingReservations(
                request.getRoomId(), request.getFechaInicio(), request.getFechaFin()
        );

        if (!conflictos.isEmpty()) {
            throw new RuntimeException("La habitación ya está reservada en las fechas seleccionadas");
        }

        BigDecimal total = precio.getPrecioPorNoche().multiply(BigDecimal.valueOf(noches));

        ReservationModel model = new ReservationModel();
        model.setUserId(currentUser.getId());
        model.setRoomId(request.getRoomId());
        model.setFechaInicio(request.getFechaInicio());
        model.setFechaFin(request.getFechaFin());
        model.setHoraCheckin(request.getHoraCheckin());
        model.setHoraCheckout(request.getHoraCheckout());
        model.setTotalPago(total);
        model.setStatus(pendiente);

        Integer statusOcupadaId = roomStatusRepositoryPort.findByName("OCUPADA")
                .orElseThrow(() -> new RuntimeException("Estado OCUPADA no encontrado")).getId();

        roomRepositoryPort.changeRoomStatus(room.getId(), statusOcupadaId);

        return reservationRepositoryPort.save(model);
    }

    public List<ReservationModel> getMyReservations() {
        UUID currentUserId = currentUserService.getCurrentUser().getId();
        return reservationRepositoryPort.findByUserId(currentUserId);
    }

    @Transactional
    public void cancelMyReservation(UUID reservationId) {
        UserModel currentUser = currentUserService.getCurrentUser();

        ReservationModel reserva = reservationRepositoryPort.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservación no encontrada"));

        if (!reserva.getUserId().equals(currentUser.getId())) {
            throw new RuntimeException("No tienes permiso para cancelar esta reservación");
        }

        ReservationStatusModel cancelada = reservationStatusRepositoryPort.findByName("CANCELADA")
                .orElseThrow(() -> new RuntimeException("Estado CANCELADA no encontrado"));

        ReservationStatusModel anterior = reserva.getStatus();

        reserva.setStatus(cancelada);
        reservationRepositoryPort.save(reserva);

        ReservationHistoryModel history = new ReservationHistoryModel();
        history.setReservationId(reservationId);
        history.setEstadoAnterior(anterior);
        history.setEstadoNuevo(cancelada);
        history.setFechaCambio(Instant.now());
        history.setComentario("Cancelada por el cliente");
        reservationHistoryRepositoryPort.save(history);

        RoomStatusModel disponible = roomStatusRepositoryPort.findByName("DISPONIBLE")
                .orElseThrow(() -> new RuntimeException("Estado DISPONIBLE no encontrado"));

        roomRepositoryPort.changeRoomStatus(reserva.getRoomId(), disponible.getId());
    }

}

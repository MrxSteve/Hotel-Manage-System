package com.devsteve.hotel_manage_system.infra.scheduled;

import com.devsteve.hotel_manage_system.application.ports.output.ReservationRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RoomRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RoomStatusRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;
import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExpiredReservationCleaner {
    private final ReservationRepositoryPort reservationRepositoryPort;
    private final RoomRepositoryPort roomRepositoryPort;
    private final RoomStatusRepositoryPort roomStatusRepositoryPort;

    @Scheduled(cron = "0 0 0 * * *") // todos los días a medianoche
    public void scheduledReleaseRooms() {
        releaseRoomsWithExpiredReservations();
    }

    public void releaseRoomsWithExpiredReservations() {
        log.info("Iniciando limpieza de habitaciones con reservas vencidas...");

        LocalDate hoy = LocalDate.now();

        List<ReservationModel> vencidas = reservationRepositoryPort.findAllWithFechaFinBefore(hoy);

        RoomStatusModel disponible = roomStatusRepositoryPort.findByName("DISPONIBLE")
                .orElseThrow(() -> new RuntimeException("Estado DISPONIBLE no encontrado"));

        vencidas.forEach(reserva -> {
            roomRepositoryPort.changeRoomStatus(reserva.getRoomId(), disponible.getId());
            log.info("Habitación liberada: {}", reserva.getRoomId());
        });

        log.info("Proceso de limpieza completado. Habitaciones liberadas: {}", vencidas.size());
    }
}

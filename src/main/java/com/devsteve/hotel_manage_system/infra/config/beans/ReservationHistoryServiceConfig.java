package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.history.GetReservationHistoryByReservationIdUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationHistoryRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.ReservationHistoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationHistoryServiceConfig {
    @Bean
    public ReservationHistoryService reservationHistoryService(ReservationHistoryRepositoryPort reservationHistoryRepositoryPort) {
        return new ReservationHistoryService(reservationHistoryRepositoryPort);
    }

    @Bean
    public GetReservationHistoryByReservationIdUseCase getReservationHistoryByReservationIdUseCase(ReservationHistoryRepositoryPort reservationHistoryRepositoryPort) {
        return new ReservationHistoryService(reservationHistoryRepositoryPort);
    }
}

package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.status.*;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationStatusRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.ReservationStatusService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationStatusServiceConfig {
    @Bean
    public ReservationStatusService reservationStatusService(ReservationStatusRepositoryPort reservationStatusRepositoryPort) {
        return new ReservationStatusService(reservationStatusRepositoryPort);
    }

    @Bean
    public CreateReservationStatusUseCase createReservationStatusUseCase(ReservationStatusService reservationStatusService) {
        return reservationStatusService;
    }

    @Bean
    public DeleteReservationStatusUseCase deleteReservationStatusUseCase(ReservationStatusService reservationStatusService) {
        return reservationStatusService;
    }

    @Bean
    public GetAllReservationStatusUseCase getAllReservationStatusUseCase(ReservationStatusService reservationStatusService) {
        return reservationStatusService;
    }

    @Bean
    public FindByIdReservationStatusUseCase findByIdReservationStatusUseCase(ReservationStatusService reservationStatusService) {
        return reservationStatusService;
    }

    @Bean
    public FindByNameReservationStatusUseCase findByNameReservationStatusUseCase(ReservationStatusService reservationStatusService) {
        return reservationStatusService;
    }
}

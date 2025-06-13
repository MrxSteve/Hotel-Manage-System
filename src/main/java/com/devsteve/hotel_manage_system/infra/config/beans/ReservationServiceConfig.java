package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations.*;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationHistoryRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationStatusRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationServiceConfig {
    @Bean
    public ReservationService reservationService(
            ReservationRepositoryPort reservationRepositoryPort,
            ReservationStatusRepositoryPort reservationStatusRepositoryPort,
            ReservationHistoryRepositoryPort reservationHistoryRepositoryPort) {
        return new ReservationService(
                reservationRepositoryPort,
                reservationStatusRepositoryPort,
                reservationHistoryRepositoryPort);
    }

    @Bean
    public CreateReservationUseCase createReservationUseCase(ReservationService reservationService) {
        return reservationService;
    }

    @Bean
    public GetAllReservationsUseCase getAllReservationsUseCase(ReservationService reservationService) {
        return reservationService;
    }

    @Bean
    public FindReservationByIdUseCase findReservationByIdUseCase(ReservationService reservationService) {
        return reservationService;
    }

    @Bean
    public DeleteReservationUseCase deleteReservationUseCase(ReservationService reservationService) {
        return reservationService;
    }

    @Bean
    public SearchReservationsUseCase searchReservationsUseCase(ReservationService reservationService) {
        return reservationService;
    }

    @Bean
    public ChangeReservationStatusUseCase changeReservationStatusUseCase(ReservationService reservationService) {
        return reservationService;
    }
}

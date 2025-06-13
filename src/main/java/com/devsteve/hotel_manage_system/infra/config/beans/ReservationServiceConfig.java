package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations.*;
import com.devsteve.hotel_manage_system.application.ports.output.*;
import com.devsteve.hotel_manage_system.application.usecases.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationServiceConfig {
    @Bean
    public ReservationService reservationService(
            ReservationRepositoryPort reservationRepositoryPort,
            ReservationStatusRepositoryPort reservationStatusRepositoryPort,
            ReservationHistoryRepositoryPort reservationHistoryRepositoryPort,
            RoomRepositoryPort roomRepositoryPort,
            RoomPriceRepositoryPort roomPriceRepositoryPort) {
        return new ReservationService(
                reservationRepositoryPort,
                reservationStatusRepositoryPort,
                reservationHistoryRepositoryPort,
                roomRepositoryPort,
                roomPriceRepositoryPort);
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

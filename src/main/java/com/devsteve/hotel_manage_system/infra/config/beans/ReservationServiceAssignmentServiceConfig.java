package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.AssignReservationServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetReservationServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.RemoveReservationServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.ExtraServiceRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.ReservationServiceRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.ReservationServiceAssignmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationServiceAssignmentServiceConfig {

    @Bean
    public ReservationServiceAssignmentService reservationServiceAssignmentService(
            ReservationServiceRepositoryPort reservationServiceRepositoryPort,
            ExtraServiceRepositoryPort extraServiceRepositoryPort) {
        return new ReservationServiceAssignmentService(reservationServiceRepositoryPort, extraServiceRepositoryPort);
    }

    @Bean
    public AssignReservationServicesUseCase assignReservationServicesUseCase(
            ReservationServiceAssignmentService reservationServiceAssignmentService) {
        return reservationServiceAssignmentService;
    }

    @Bean
    public GetReservationServicesUseCase getReservationServicesUseCase(
            ReservationServiceAssignmentService reservationServiceAssignmentService) {
        return reservationServiceAssignmentService;
    }

    @Bean
    public RemoveReservationServiceUseCase removeReservationServiceUseCase(
            ReservationServiceAssignmentService reservationServiceAssignmentService) {
        return reservationServiceAssignmentService;
    }
}

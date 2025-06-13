package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.CreateExtraServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.DeleteExtraServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetActiveExtraServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetAllExtraServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.ExtraServiceRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.ExtraServiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtraServiceConfig {

    @Bean
    public ExtraServiceService extraServiceService(ExtraServiceRepositoryPort extraServiceRepositoryPort) {
        return new ExtraServiceService(extraServiceRepositoryPort);
    }

    @Bean
    public CreateExtraServiceUseCase createExtraServiceUseCase(ExtraServiceService extraServiceService) {
        return extraServiceService;
    }

    @Bean
    public GetAllExtraServicesUseCase getAllExtraServicesUseCase(ExtraServiceService extraServiceService) {
        return extraServiceService;
    }

    @Bean
    public GetActiveExtraServicesUseCase getActiveExtraServicesUseCase(ExtraServiceService extraServiceService) {
        return extraServiceService;
    }

    @Bean
    public DeleteExtraServiceUseCase deleteExtraServiceUseCase(ExtraServiceService extraServiceService) {
        return extraServiceService;
    }
}

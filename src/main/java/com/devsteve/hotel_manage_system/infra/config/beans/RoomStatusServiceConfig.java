package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.room.status.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomStatusRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RoomStatusService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomStatusServiceConfig {
    @Bean
    public RoomStatusService roomStatusService(RoomStatusRepositoryPort roomStatusRepositoryPort) {
        return new RoomStatusService(roomStatusRepositoryPort);
    }

    @Bean
    public CreateRoomStatusUseCase createRoomStatusUseCase(RoomStatusService roomStatusService) {
        return roomStatusService;
    }

    @Bean
    public GetAllRoomStatusUseCase getAllRoomStatusUseCase(RoomStatusService roomStatusService) {
        return roomStatusService;
    }

    @Bean
    public DeleteRoomStatusUseCase deleteRoomStatusUseCase(RoomStatusService roomStatusService) {
        return roomStatusService;
    }

    @Bean
    public FindByIdRoomStatusUseCase findByIdRoomStatusUseCase(RoomStatusService roomStatusService) {
        return roomStatusService;
    }

    @Bean
    public FindByNameRoomStatusUseCase findByNameRoomStatusUseCase(RoomStatusService roomStatusService) {
        return roomStatusService;
    }
}

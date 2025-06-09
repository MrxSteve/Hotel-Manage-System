package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.room.type.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomTypeRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RoomTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomTypeServiceConfig {
    @Bean
    public RoomTypeService roomTypeService(RoomTypeRepositoryPort roomTypeRepositoryPort) {
        return new RoomTypeService(roomTypeRepositoryPort);
    }

    @Bean
    public CreateRoomTypeUseCase createRoomTypeUseCase(RoomTypeService roomTypeService) {
        return roomTypeService;
    }

    @Bean
    public DeleteRoomTypeUseCase deleteRoomTypeUseCase(RoomTypeService roomTypeService) {
        return roomTypeService;
    }

    @Bean
    public FindByIdRoomTypeUseCase findByIdRoomTypeUseCase(RoomTypeService roomTypeService) {
        return roomTypeService;
    }

    @Bean
    public FindByNameRoomTypeUseCase findByNameRoomTypeUseCase(RoomTypeService roomTypeService) {
        return roomTypeService;
    }

    @Bean
    public GetAllRoomTypeUseCase getAllRoomTypeUseCase(RoomTypeService roomTypeService) {
        return roomTypeService;
    }

    @Bean
    public UpdateRoomTypeUseCase updateRoomTypeUseCase(RoomTypeService roomTypeService) {
        return roomTypeService;
    }
}

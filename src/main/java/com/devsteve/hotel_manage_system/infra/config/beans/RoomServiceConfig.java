package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.room.rooms.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RoomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomServiceConfig {
    @Bean
    public RoomService roomService(RoomRepositoryPort roomRepositoryPort) {
        return new RoomService(roomRepositoryPort);
    }

    @Bean
    public CreateRoomUseCase createRoomUseCase(RoomService roomService) {
        return roomService;
    }

    @Bean
    public UpdateRoomUseCase updateRoomUseCase(RoomService roomService) {
        return roomService;
    }

    @Bean
    public DeleteRoomUseCase deleteRoomUseCase(RoomService roomService) {
        return roomService;
    }

    @Bean
    public GetRoomByIdUseCase getRoomByIdUseCase(RoomService roomService) {
        return roomService;
    }

    @Bean
    public ListRoomsUseCase listRoomsUseCase(RoomService roomService) {
        return roomService;
    }

    @Bean
    public GetRoomsByFilterUseCase getRoomsByFilterUseCase(RoomService roomService) {
        return roomService;
    }
}

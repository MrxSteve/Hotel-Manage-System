package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.room.price.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomPriceRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RoomPriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomPriceServiceConfig {
    @Bean
    public RoomPriceService roomPriceService(RoomPriceRepositoryPort roomPriceRepositoryPort) {
        return new RoomPriceService(roomPriceRepositoryPort);
    }

    @Bean
    public CreateRoomPriceUseCase createRoomPriceUseCase(RoomPriceService roomPriceService) {
        return roomPriceService;
    }

    @Bean
    public DeleteRoomPriceUseCase deleteRoomPriceUseCase(RoomPriceService roomPriceService) {
        return roomPriceService;
    }

    @Bean
    public GetAllRoomPricesUseCase getAllRoomPricesUseCase(RoomPriceService roomPriceService) {
        return roomPriceService;
    }

    @Bean
    public FindRoomPriceByIdUseCase findRoomPriceByIdUseCase(RoomPriceService roomPriceService) {
        return roomPriceService;
    }

    @Bean
    public FindRoomPricesByRoomTypeIdUseCase findRoomPricesByRoomTypeIdUseCase(RoomPriceService roomPriceService) {
        return roomPriceService;
    }
}

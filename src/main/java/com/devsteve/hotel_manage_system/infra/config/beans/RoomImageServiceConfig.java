package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.room.images.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomImageRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RoomRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RoomImageService;
import com.devsteve.hotel_manage_system.infra.adapters.output.external.S3ImageStorageAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomImageServiceConfig {
    @Bean
    public RoomImageService roomImageService(
            RoomImageRepositoryPort roomImageRepositoryPort,
            S3ImageStorageAdapter s3ImageStorageAdapter,
            RoomRepositoryPort roomRepositoryPort) {
        return new RoomImageService(
                roomImageRepositoryPort,
                s3ImageStorageAdapter,
                roomRepositoryPort);
    }

    @Bean
    public UploadImageUseCase uploadImageUseCase(RoomImageService roomImageService) {
        return roomImageService;
    }

    @Bean
    public GetImagesByRoomIdUseCase getImagesByRoomIdUseCase(RoomImageService roomImageService) {
        return roomImageService;
    }

    @Bean
    public DeleteImageByIdUseCase deleteImageByIdUseCase(RoomImageService roomImageService) {
        return roomImageService;
    }

    @Bean
    public DeleteImageByIdAndRoomIdUseCase deleteImageByIdAndRoomIdUseCase(RoomImageService roomImageService) {
        return roomImageService;
    }

    @Bean
    public DeleteAllImagesByRoomIdUseCase deleteAllImagesByRoomIdUseCase(RoomImageService roomImageService) {
        return roomImageService;
    }
}

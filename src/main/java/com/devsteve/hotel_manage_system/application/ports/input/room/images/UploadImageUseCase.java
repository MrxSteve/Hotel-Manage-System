package com.devsteve.hotel_manage_system.application.ports.input.room.images;

import com.devsteve.hotel_manage_system.domain.models.room.RoomImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UploadImageUseCase {
    RoomImageModel uploadImage(UUID roomId, MultipartFile image);
}

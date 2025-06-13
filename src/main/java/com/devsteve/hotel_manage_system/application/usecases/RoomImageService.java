package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.room.images.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoomImageRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RoomRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.room.RoomImageModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.external.S3ImageStorageAdapter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class RoomImageService implements
        UploadImageUseCase,
        GetImagesByRoomIdUseCase,
        DeleteImageByIdUseCase,
        DeleteImageByIdAndRoomIdUseCase,
        DeleteAllImagesByRoomIdUseCase {

    private final RoomImageRepositoryPort roomImageRepositoryPort;
    private final S3ImageStorageAdapter s3ImageStorageAdapter;
    private final RoomRepositoryPort roomRepositoryPort;

    public RoomImageService(
            RoomImageRepositoryPort roomImageRepositoryPort,
            S3ImageStorageAdapter s3ImageStorageAdapter,
            RoomRepositoryPort roomRepositoryPort) {
        this.roomImageRepositoryPort = roomImageRepositoryPort;
        this.s3ImageStorageAdapter = s3ImageStorageAdapter;
        this.roomRepositoryPort = roomRepositoryPort;
    }

    @Override
    public RoomImageModel uploadImage(UUID roomId, MultipartFile image) {
        roomRepositoryPort.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));

        try {
            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();

            String urlImagen = s3ImageStorageAdapter.uploadImage(
                    filename,
                    image.getBytes(),
                    image.getContentType()
            );

            RoomImageModel model = new RoomImageModel();
            model.setRoomId(roomId);
            model.setUrlImagen(urlImagen);

            return roomImageRepositoryPort.save(model);

        } catch (IOException e) {
            throw new RuntimeException("Error al leer la imagen", e);
        }
    }

    @Override
    public List<RoomImageModel> getImagesByRoomId(UUID roomId) {
        return roomImageRepositoryPort.findByRoomId(roomId);
    }

    @Override
    public void deleteImageById(Integer imageId) {
        RoomImageModel image = roomImageRepositoryPort.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String key = extractKeyFromUrl(image.getUrlImagen());
        s3ImageStorageAdapter.deleteImage(key);

        roomImageRepositoryPort.deleteById(imageId);
    }

    @Override
    public void deleteImageByIdAndRoomId(Integer imageId, UUID roomId) {
        RoomImageModel image = roomImageRepositoryPort.findByIdAndRoomId(imageId, roomId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada con el id y roomId proporcionados"));

        String key = extractKeyFromUrl(image.getUrlImagen());
        s3ImageStorageAdapter.deleteImage(key);

        roomImageRepositoryPort.deleteByIdAndRoomId(imageId, roomId);
    }

    @Override
    public void deleteAllImagesByRoomId(UUID roomId) {
        List<RoomImageModel> images = roomImageRepositoryPort.findByRoomId(roomId);

        if (images.isEmpty()) {
            return;
        }

        images.forEach(image -> {
            String key = extractKeyFromUrl(image.getUrlImagen());
            s3ImageStorageAdapter.deleteImage(key);
        });

        roomImageRepositoryPort.deleteAllByRoomId(roomId);
    }

    private String extractKeyFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

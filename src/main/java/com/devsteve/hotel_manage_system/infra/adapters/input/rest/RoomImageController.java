package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.room.images.*;
import com.devsteve.hotel_manage_system.domain.models.room.RoomImageModel;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomImageResponse;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomImageMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room-images")
@RequiredArgsConstructor
public class RoomImageController {
    private final UploadImageUseCase uploadImageUseCase;
    private final GetImagesByRoomIdUseCase getImagesByRoomIdUseCase;
    private final DeleteImageByIdUseCase deleteImageByIdUseCase;
    private final DeleteImageByIdAndRoomIdUseCase deleteImageByIdAndRoomIdUseCase;
    private final DeleteAllImagesByRoomIdUseCase deleteAllImagesByRoomIdUseCase;
    private final RoomImageMapper roomImageMapper;

    @Operation(summary = "Subir imagen a una habitación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imagen subida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Habitación no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno")
    })
    @PostMapping(value = "/{roomId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomImageResponse> uploadImage(
            @PathVariable UUID roomId,
            @Parameter(description = "Archivo de imagen", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @RequestParam("image") MultipartFile image
    ) {
        RoomImageModel model = uploadImageUseCase.uploadImage(roomId, image);
        return ResponseEntity.ok(roomImageMapper.modelToResponse(model));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<RoomImageResponse>> getImages(@PathVariable UUID roomId) {
        List<RoomImageModel> models = getImagesByRoomIdUseCase.getImagesByRoomId(roomId);
        return ResponseEntity.ok(roomImageMapper.toResponseList(models));
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer imageId) {
        deleteImageByIdUseCase.deleteImageById(imageId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roomId}/images/{imageId}")
    public ResponseEntity<Void> deleteByIdAndRoomId(
            @PathVariable UUID roomId,
            @PathVariable Integer imageId) {
        deleteImageByIdAndRoomIdUseCase.deleteImageByIdAndRoomId(imageId, roomId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roomId}/all")
    public ResponseEntity<Void> deleteAllByRoomId(@PathVariable UUID roomId) {
        deleteAllImagesByRoomIdUseCase.deleteAllImagesByRoomId(roomId);
        return ResponseEntity.noContent().build();
    }
}

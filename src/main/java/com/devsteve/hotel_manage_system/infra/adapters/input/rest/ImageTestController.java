package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.output.ImageStoragePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageTestController {

    private final ImageStoragePort imageStoragePort;

    @Operation(summary = "Sube una imagen al bucket S3")
    @ApiResponse(responseCode = "200", description = "URL publica de la imagen")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = imageStoragePort.uploadImage(
                file.getOriginalFilename(),
                file.getBytes(),
                file.getContentType()
        );
        return ResponseEntity.ok(url);
    }

    @Operation(summary = "Elimina una imagen del bucket S3")
    @ApiResponse(responseCode = "204", description = "Imagen eliminada")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam String key) {
        imageStoragePort.deleteImage(key);
        return ResponseEntity.noContent().build();
    }
}


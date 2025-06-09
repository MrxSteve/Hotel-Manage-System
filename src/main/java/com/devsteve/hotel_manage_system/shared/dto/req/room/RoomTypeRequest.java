package com.devsteve.hotel_manage_system.shared.dto.req.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomTypeRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must be at most 50 characters long")
    private String nombre;

    @Size(max = 255, message = "Description must be at most 255 characters long")
    private String descripcion;
}

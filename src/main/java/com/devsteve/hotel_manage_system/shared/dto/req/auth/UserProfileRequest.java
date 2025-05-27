package com.devsteve.hotel_manage_system.shared.dto.req.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserProfileRequest {
    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotBlank(message = "Name cannot be blank")
    private String nombreCompleto;

    @NotBlank(message = "DUI cannot be blank")
    private String dui;

    @NotBlank(message = "Phone number cannot be blank")
    private String telefono;

    private String direccion;
    private LocalDate fechaNacimiento;
    private String genero;
    private String nacionalidad;
}

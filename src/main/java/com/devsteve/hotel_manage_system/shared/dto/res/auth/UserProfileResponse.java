package com.devsteve.hotel_manage_system.shared.dto.res.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserProfileResponse {
    private UUID id;
    private UUID userId;
    private String nombreCompleto;
    private String dui;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String genero;
    private String nacionalidad;
}

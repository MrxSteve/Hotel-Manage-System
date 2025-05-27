package com.devsteve.hotel_manage_system.shared.dto.req.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UpdateUserProfileRequest {
    private String nombreCompleto;
    private String dui;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String genero;
    private String nacionalidad;
}

package com.devsteve.hotel_manage_system.shared.dto.req.reservation;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ExtraServiceRequest {
    @NotBlank(message = "Nombre is required")
    private String nombre;

    private String descripcion;

    @NotNull(message = "Precio is required")
    @DecimalMin(value = "0.0", message = "Precio must be greater than or equal to 0.0")
    private BigDecimal precio;

    private Boolean activo = true;
}

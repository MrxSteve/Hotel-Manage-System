package com.devsteve.hotel_manage_system.shared.dto.res.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ExtraServiceResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean activo;
}

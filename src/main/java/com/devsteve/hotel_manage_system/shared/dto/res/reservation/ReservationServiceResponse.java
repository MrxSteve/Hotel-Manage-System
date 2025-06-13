package com.devsteve.hotel_manage_system.shared.dto.res.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ReservationServiceResponse {
    private UUID reservationId;
    private Integer serviceId;
    private String serviceNombre;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total;
}

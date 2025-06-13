package com.devsteve.hotel_manage_system.infra.security.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ReservationClientRequest {
    @NotNull(message = "Room ID cannot be null")
    private UUID roomId;

    @NotNull(message = "Start date cannot be null")
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaCheckin;
    private LocalTime horaCheckout;

}

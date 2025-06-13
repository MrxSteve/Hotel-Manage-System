package com.devsteve.hotel_manage_system.shared.dto.res.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ReservationResponse {
    private UUID id;
    private UUID userId;
    private UUID roomId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaCheckin;
    private LocalTime horaCheckout;
    private ReservationStatusResponse status;
    private BigDecimal totalPago;
    private Instant createdAt;
}

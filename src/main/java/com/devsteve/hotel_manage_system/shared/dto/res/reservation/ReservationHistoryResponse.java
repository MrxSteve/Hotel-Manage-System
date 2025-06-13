package com.devsteve.hotel_manage_system.shared.dto.res.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ReservationHistoryResponse {
    private Integer id;
    private UUID reservationId;
    private ReservationStatusResponse estadoAnterior;
    private ReservationStatusResponse estadoNuevo;
    private Instant fechaCambio;
    private String comentario;
}

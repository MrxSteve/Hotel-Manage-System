package com.devsteve.hotel_manage_system.shared.dto.req.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ReservationHistoryRequest {
    private UUID reservationId;
    private Integer estadoAnterior;
    private Integer estadoNuevo;
    private String comentario;
}

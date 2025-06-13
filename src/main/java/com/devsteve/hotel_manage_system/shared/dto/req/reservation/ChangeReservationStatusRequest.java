package com.devsteve.hotel_manage_system.shared.dto.req.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ChangeReservationStatusRequest {
    private Integer nuevoStatusId;
    private String comentario;
}

package com.devsteve.hotel_manage_system.shared.dto.req.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UpdateRoomRequest {
    private Integer numeroHabitacion;
    private Integer capacidad;
    private String descripcion;
    private Integer roomTypeId;
    private Integer statusId;
}

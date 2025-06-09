package com.devsteve.hotel_manage_system.shared.dto.res.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomTypeResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
}

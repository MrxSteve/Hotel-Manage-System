package com.devsteve.hotel_manage_system.shared.dto.res.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomResponse {
    private UUID id;
    private Integer numeroHabitacion;
    private Integer capacidad;
    private String descripcion;
    private Instant createdAt;
    private Instant updatedAt;

    private RoomTypeResponse roomType;
    private RoomStatusResponse status;
    private List<RoomPriceResponse> precios;
    private List<RoomImageResponse> imagenes;
}

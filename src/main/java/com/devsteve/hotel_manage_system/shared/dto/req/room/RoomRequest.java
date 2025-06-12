package com.devsteve.hotel_manage_system.shared.dto.req.room;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomRequest {
    @NotNull(message = "numeroHabitacion is required")
    @Positive(message = "numeroHabitacion must be a positive integer")
    private Integer numeroHabitacion;

    @NotNull(message = "capacidad is required")
    @Positive(message = "capacidad must be a positive integer")
    private Integer capacidad;

    private String descripcion;

    @NotNull(message = "roomTypeId is required")
    @Positive(message = "roomTypeId must be a positive integer")
    private Integer roomTypeId;

    @NotNull(message = "statusId is required")
    @Positive(message = "statusId must be a positive integer")
    private Integer statusId;
}

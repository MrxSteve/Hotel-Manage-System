package com.devsteve.hotel_manage_system.shared.dto.res.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomImageResponse {
    private Integer id;
    private UUID roomId;
    private String urlImagen;
}

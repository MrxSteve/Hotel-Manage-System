package com.devsteve.hotel_manage_system.shared.dto.res.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomPriceResponse {
    private Integer id;
    private Integer roomTypeId;
    private String roomTypeNombre;
    private BigDecimal precioPorNoche;
    private String vigenteDesde;
    private String vigenteHasta;
}

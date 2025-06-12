package com.devsteve.hotel_manage_system.shared.dto.req.room;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoomPriceRequest {
    @NotNull(message = "roomTypeId is required")
    private Integer roomTypeId;

    @NotNull(message = "precioPorNoche is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "precioPorNoche must be greater than or equal to 0")
    private BigDecimal precioPorNoche;

    @NotNull(message = "vigenteDesde is required")
    private LocalDate vigenteDesde;
    private LocalDate vigenteHasta;
}

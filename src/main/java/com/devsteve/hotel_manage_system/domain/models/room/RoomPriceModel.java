package com.devsteve.hotel_manage_system.domain.models.room;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RoomPriceModel {
    private Integer id;
    private RoomTypeModel roomType;
    private BigDecimal precioPorNoche;
    private LocalDate vigenteDesde;
    private LocalDate vigenteHasta;

    public RoomPriceModel() {
    }

    public RoomPriceModel(Integer id, RoomTypeModel roomType, BigDecimal precioPorNoche, LocalDate vigenteDesde, LocalDate vigenteHasta) {
        this.id = id;
        this.roomType = roomType;
        this.precioPorNoche = precioPorNoche;
        this.vigenteDesde = vigenteDesde;
        this.vigenteHasta = vigenteHasta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomTypeModel getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeModel roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public LocalDate getVigenteDesde() {
        return vigenteDesde;
    }

    public void setVigenteDesde(LocalDate vigenteDesde) {
        this.vigenteDesde = vigenteDesde;
    }

    public LocalDate getVigenteHasta() {
        return vigenteHasta;
    }

    public void setVigenteHasta(LocalDate vigenteHasta) {
        this.vigenteHasta = vigenteHasta;
    }
}

package com.devsteve.hotel_manage_system.domain.models.room;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class RoomModel {
    private UUID id;
    private Integer numeroHabitacion;
    private Integer capacidad;
    private String descripcion;
    private Instant createdAt;
    private Instant updatedAt;

    private RoomTypeModel roomType;
    private RoomStatusModel status;
    private List<RoomPriceModel> precios;

    public RoomModel() {
    }

    public RoomModel(UUID id, Integer numeroHabitacion, Integer capacidad, String descripcion, Instant createdAt, Instant updatedAt, RoomTypeModel roomType, RoomStatusModel status, List<RoomPriceModel> precios) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roomType = roomType;
        this.status = status;
        this.precios = precios;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(Integer numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public RoomTypeModel getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeModel roomType) {
        this.roomType = roomType;
    }

    public RoomStatusModel getStatus() {
        return status;
    }

    public void setStatus(RoomStatusModel status) {
        this.status = status;
    }

    public List<RoomPriceModel> getPrecios() {
        return precios;
    }

    public void setPrecios(List<RoomPriceModel> precios) {
        this.precios = precios;
    }
}

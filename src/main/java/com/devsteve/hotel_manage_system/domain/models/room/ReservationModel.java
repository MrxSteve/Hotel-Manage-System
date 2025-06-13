package com.devsteve.hotel_manage_system.domain.models.room;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ReservationModel {
    private UUID id;
    private UUID userId;
    private UUID roomId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaCheckin;
    private LocalTime horaCheckout;
    private Integer statusId;
    private ReservationStatusModel status;
    private BigDecimal totalPago;
    private Instant createdAt;

    public ReservationModel() {
    }

    public ReservationModel(UUID id, UUID userId, UUID roomId, LocalDate fechaInicio, LocalDate fechaFin,
                            LocalTime horaCheckin, LocalTime horaCheckout, Integer statusId,
                            ReservationStatusModel status, BigDecimal totalPago, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaCheckin = horaCheckin;
        this.horaCheckout = horaCheckout;
        this.statusId = statusId;
        this.status = status;
        this.totalPago = totalPago;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHoraCheckin() {
        return horaCheckin;
    }

    public void setHoraCheckin(LocalTime horaCheckin) {
        this.horaCheckin = horaCheckin;
    }

    public LocalTime getHoraCheckout() {
        return horaCheckout;
    }

    public void setHoraCheckout(LocalTime horaCheckout) {
        this.horaCheckout = horaCheckout;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public ReservationStatusModel getStatus() {
        return status;
    }

    public void setStatus(ReservationStatusModel status) {
        this.status = status;
    }

    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(BigDecimal totalPago) {
        this.totalPago = totalPago;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

package com.devsteve.hotel_manage_system.domain.models.reservation;

import java.math.BigDecimal;
import java.util.UUID;

public class ReservationServiceModel {
    private UUID reservationId;
    private Integer serviceId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private String serviceNombre;

    public ReservationServiceModel() {
    }

    public ReservationServiceModel(UUID reservationId, Integer serviceId, Integer cantidad, BigDecimal precioUnitario, String serviceNombre) {
        this.reservationId = reservationId;
        this.serviceId = serviceId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.serviceNombre = serviceNombre;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getServiceNombre() {
        return serviceNombre;
    }

    public void setServiceNombre(String serviceNombre) {
        this.serviceNombre = serviceNombre;
    }
}

package com.devsteve.hotel_manage_system.domain.models.reservation;

import java.math.BigDecimal;

public class ExtraServiceModel {
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Boolean activo;

    public ExtraServiceModel() {
    }

    public ExtraServiceModel(Integer id, String nombre, String descripcion, BigDecimal precio, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

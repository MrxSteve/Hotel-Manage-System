package com.devsteve.hotel_manage_system.domain.models.auth;

import java.time.LocalDate;
import java.util.UUID;

public class UserProfileModel {
    private UUID id;
    private UUID userId;
    private String nombreCompleto;
    private String dui;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String genero;
    private String nacionalidad;

    public UserProfileModel() {
    }

    public UserProfileModel(UUID id, UUID userId, String nombreCompleto,
                            String dui, String telefono, String direccion,
                            LocalDate fechaNacimiento, String genero, String nacionalidad) {
        this.id = id;
        this.userId = userId;
        this.nombreCompleto = nombreCompleto;
        this.dui = dui;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}

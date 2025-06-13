package com.devsteve.hotel_manage_system.domain.models.reservation;

import java.time.Instant;
import java.util.UUID;

public class ReservationHistoryModel {
    private Integer id;
    private UUID reservationId;
    private ReservationStatusModel estadoAnterior;
    private ReservationStatusModel estadoNuevo;
    private Instant fechaCambio;
    private String comentario;

    public ReservationHistoryModel() {
    }

    public ReservationHistoryModel(Integer id, UUID reservationId, ReservationStatusModel estadoAnterior,
                                   ReservationStatusModel estadoNuevo, Instant fechaCambio, String comentario) {
        this.id = id;
        this.reservationId = reservationId;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.fechaCambio = fechaCambio;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public ReservationStatusModel getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(ReservationStatusModel estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public ReservationStatusModel getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(ReservationStatusModel estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Instant getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Instant fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}

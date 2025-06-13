package com.devsteve.hotel_manage_system.domain.models.reservation;

public class ReservationStatusModel {
    private Integer id;
    private String name;

    public ReservationStatusModel() {
    }

    public ReservationStatusModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

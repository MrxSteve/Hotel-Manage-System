package com.devsteve.hotel_manage_system.domain.models.room;

public class RoomStatusModel {
    private Integer id;
    private String name;

    public RoomStatusModel() {
    }

    public RoomStatusModel(Integer id, String name) {
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

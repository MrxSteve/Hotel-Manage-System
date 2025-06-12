package com.devsteve.hotel_manage_system.domain.models.room;

import java.util.UUID;

public class RoomImageModel {
    private Integer id;
    private UUID roomId;
    private String urlImagen;

    public RoomImageModel() {
    }

    public RoomImageModel(Integer id, UUID roomId, String urlImagen) {
        this.id = id;
        this.roomId = roomId;
        this.urlImagen = urlImagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}

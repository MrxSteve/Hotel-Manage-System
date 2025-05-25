package com.devsteve.hotel_manage_system.domain.models.auth;

public class PermissionModel {
    private Integer id;
    private String name;

    public PermissionModel() {
    }

    public PermissionModel(Integer id, String name) {
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

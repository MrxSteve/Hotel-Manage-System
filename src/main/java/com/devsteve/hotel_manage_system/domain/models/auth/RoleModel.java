package com.devsteve.hotel_manage_system.domain.models.auth;

public class RoleModel {
    private Integer id;
    private String name;

    public RoleModel() {
    }

    public RoleModel(Integer id, String name) {
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

package com.devsteve.hotel_manage_system.domain.models.auth;

public class RolePermissionModel {
    private RolePermissionIdModel id;
    private RoleModel role;
    private PermissionModel permission;

    public RolePermissionModel() {
    }

    public RolePermissionModel(RolePermissionIdModel id, RoleModel role, PermissionModel permission) {
        this.id = id;
        this.role = role;
        this.permission = permission;
    }

    public RolePermissionIdModel getId() {
        return id;
    }

    public void setId(RolePermissionIdModel id) {
        this.id = id;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public PermissionModel getPermission() {
        return permission;
    }

    public void setPermission(PermissionModel permission) {
        this.permission = permission;
    }
}

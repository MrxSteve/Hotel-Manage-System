package com.devsteve.hotel_manage_system.domain.models.auth;

public class UserRoleModel {
    private UserRoleIdModel id;
    private UserModel user;
    private RoleModel role;

    public UserRoleModel() {
    }

    public UserRoleModel(UserRoleIdModel id, UserModel user, RoleModel role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public UserRoleIdModel getId() {
        return id;
    }

    public void setId(UserRoleIdModel id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }
}

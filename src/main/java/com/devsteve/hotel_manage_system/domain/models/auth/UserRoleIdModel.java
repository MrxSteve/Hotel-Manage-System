package com.devsteve.hotel_manage_system.domain.models.auth;

import java.util.Objects;
import java.util.UUID;

public class UserRoleIdModel {
    private UUID userId;
    private Integer roleId;

    public UserRoleIdModel() {
    }

    public UserRoleIdModel(UUID userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserRoleIdModel that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}

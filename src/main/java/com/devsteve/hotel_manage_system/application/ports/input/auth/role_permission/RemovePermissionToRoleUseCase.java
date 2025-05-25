package com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission;

public interface RemovePermissionToRoleUseCase {
    void removePermissionToRole(Integer roleId, Integer permissionId);
}

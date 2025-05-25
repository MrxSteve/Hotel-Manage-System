package com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission;

public interface AssignPermissionToRoleUseCase {
    void assignPermissionToRole(Integer roleId, Integer permissionId);
}

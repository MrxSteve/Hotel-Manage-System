package com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission;

import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;

import java.util.List;

public interface GetPermissionsByRoleUseCase {
    List<PermissionModel> getPermissionsByRole(Integer roleId, int page, int size);
}

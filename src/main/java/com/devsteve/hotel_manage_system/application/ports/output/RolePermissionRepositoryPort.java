package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.RolePermissionModel;

import java.util.List;

public interface RolePermissionRepositoryPort {
    RolePermissionModel save(RolePermissionModel rolePermissionModel);
    void deleteByRoleIdAndPermissionId(Integer roleId, Integer permissionId);
    List<RolePermissionModel> findByRoleId(Integer roleId, int page, int size);
    boolean existsByRoleIdAndPermissionId(Integer roleId, Integer permissionId);
}

package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.AssignPermissionToRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.GetPermissionsByRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.RemovePermissionToRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.PermissionRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RolePermissionRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RoleRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.domain.models.auth.RolePermissionIdModel;
import com.devsteve.hotel_manage_system.domain.models.auth.RolePermissionModel;

import java.util.List;

public class RolePermissionService implements
        AssignPermissionToRoleUseCase,
        RemovePermissionToRoleUseCase,
        GetPermissionsByRoleUseCase {
    private final RolePermissionRepositoryPort rolePermissionRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PermissionRepositoryPort permissionRepositoryPort;

    public RolePermissionService(
            RolePermissionRepositoryPort rolePermissionRepositoryPort,
            RoleRepositoryPort roleRepositoryPort,
            PermissionRepositoryPort permissionRepositoryPort) {
        this.rolePermissionRepositoryPort = rolePermissionRepositoryPort;
        this.roleRepositoryPort = roleRepositoryPort;
        this.permissionRepositoryPort = permissionRepositoryPort;
    }

    @Override
    public void assignPermissionToRole(Integer roleId, Integer permissionId) {
        this.validateRoleAndPermissionExist(roleId, permissionId);

        if (rolePermissionRepositoryPort.existsByRoleIdAndPermissionId(roleId, permissionId)) {
            throw new IllegalArgumentException("Permission with id " + permissionId + " is already assigned to role with id " + roleId);
        }

        RolePermissionIdModel idModel = new RolePermissionIdModel(roleId, permissionId);

        RoleModel role = new RoleModel();
        role.setId(roleId);

        PermissionModel permission = new PermissionModel();
        permission.setId(permissionId);

        RolePermissionModel model = new RolePermissionModel(idModel, role, permission);
        rolePermissionRepositoryPort.save(model);
    }

    @Override
    public void removePermissionToRole(Integer roleId, Integer permissionId) {
        this.validateRoleAndPermissionExist(roleId, permissionId);

        if (!rolePermissionRepositoryPort.existsByRoleIdAndPermissionId(roleId, permissionId)) {
            throw new IllegalArgumentException("Permission with id " + permissionId + " is not assigned to role with id " + roleId);
        }

        rolePermissionRepositoryPort.deleteByRoleIdAndPermissionId(roleId, permissionId);
    }

    @Override
    public List<PermissionModel> getPermissionsByRole(Integer roleId, int page, int size) {
        return rolePermissionRepositoryPort.findByRoleId(roleId, page, size)
                .stream()
                .map(RolePermissionModel::getPermission)
                .toList();
    }

    private void validateRoleAndPermissionExist(Integer roleId, Integer permissionId) {
        if (!roleRepositoryPort.findById(roleId).isPresent()) {
            throw new IllegalArgumentException("Role with id " + roleId + " does not exist");
        }

        if (!permissionRepositoryPort.findById(permissionId).isPresent()) {
            throw new IllegalArgumentException("Permission with id " + permissionId + " does not exist");
        }
    }
}

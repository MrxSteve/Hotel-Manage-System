package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.auth.permission.*;
import com.devsteve.hotel_manage_system.application.ports.output.PermissionRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;

import java.util.List;

public class PermissionService implements
        CreatePermissionUseCase,
        FindPermissionByIdUseCase,
        FindAllPermissionUseCase,
        FindPermissionByNameUseCase,
        DeletePermissionUseCase {
    private final PermissionRepositoryPort permissionRepositoryPort;

    public PermissionService(PermissionRepositoryPort permissionRepositoryPort) {
        this.permissionRepositoryPort = permissionRepositoryPort;
    }

    @Override
    public PermissionModel createPermission(PermissionModel permissionModel) {
        if (permissionRepositoryPort.existsByName(permissionModel.getName())) {
            throw new IllegalArgumentException("Permission with name " + permissionModel.getName() + " already exists");
        }

        return permissionRepositoryPort.save(permissionModel);
    }

    @Override
    public PermissionModel findById(Integer permissionId) {
        return permissionRepositoryPort.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + permissionId));
    }

    @Override
    public void deletePermission(Integer permissionId) {
        this.findById(permissionId);
        permissionRepositoryPort.deleteById(permissionId);
    }

    @Override
    public List<PermissionModel> findAll(int page, int size) {
        return permissionRepositoryPort.findAll(page, size);
    }

    @Override
    public List<PermissionModel> findByName(String name, int page, int size) {
        return permissionRepositoryPort.findByName(name, page, size);
    }
}

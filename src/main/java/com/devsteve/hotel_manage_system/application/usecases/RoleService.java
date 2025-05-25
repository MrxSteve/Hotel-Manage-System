package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.auth.role.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoleRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;

import java.util.List;

public class RoleService implements
        RoleCreateUseCase,
        RoleFindAllUseCase,
        RoleFindByIdUseCase,
        RoleDeleteUseCase,
        RoleFindByNameUseCase
{
    private final RoleRepositoryPort roleRepositoryPort;

    public RoleService(RoleRepositoryPort roleRepositoryPort) {
        this.roleRepositoryPort = roleRepositoryPort;
    }

    @Override
    public RoleModel createRole(RoleModel model) {
        if (roleRepositoryPort.existsByName(model.getName())) {
            throw new IllegalArgumentException("Role with name " + model.getName() + " already exists");
        }
        return roleRepositoryPort.save(model);
    }

    @Override
    public RoleModel findById(Integer roleId) {
        return roleRepositoryPort.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
    }

    @Override
    public void deleteRole(Integer roleId) {
        this.findById(roleId);
        roleRepositoryPort.deleteById(roleId);
    }

    @Override
    public List<RoleModel> findAllRoles(int page, int size) {
        return roleRepositoryPort.findAll(page, size);
    }

    @Override
    public RoleModel findByName(String name) {
        return roleRepositoryPort.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + name));
    }
}

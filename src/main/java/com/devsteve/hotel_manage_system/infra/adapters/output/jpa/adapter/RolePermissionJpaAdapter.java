package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RolePermissionRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RolePermissionModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RolePermissionJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.RolePermission;
import com.devsteve.hotel_manage_system.infra.entities.RolePermissionId;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RolePermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolePermissionJpaAdapter implements RolePermissionRepositoryPort {
    private final RolePermissionJpaRepository rolePermissionJpaRepository;
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public RolePermissionModel save(RolePermissionModel rolePermissionModel) {
        RolePermission entity = rolePermissionMapper.modelToEntity(rolePermissionModel);
        RolePermission savedEntity = rolePermissionJpaRepository.save(entity);

        return rolePermissionMapper.entityToModel(savedEntity);
    }

    @Override
    public void deleteByRoleIdAndPermissionId(Integer roleId, Integer permissionId) {
        RolePermissionId id = new RolePermissionId(roleId, permissionId);
        rolePermissionJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByRoleIdAndPermissionId(Integer roleId, Integer permissionId) {
        RolePermissionId id = new RolePermissionId(roleId, permissionId);
        return rolePermissionJpaRepository.existsById(id);
    }

    @Override
    public List<RolePermissionModel> findByRoleId(Integer roleId, int page, int size) {
        return rolePermissionJpaRepository.findByRole_Id(roleId, PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(rolePermissionMapper::entityToModel)
                .toList();
    }
}

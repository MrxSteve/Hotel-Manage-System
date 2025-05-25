package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.PermissionRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.PermissionJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.Permission;
import com.devsteve.hotel_manage_system.shared.mappers.auth.PermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PermissionJpaAdapter implements PermissionRepositoryPort {
    private final PermissionJpaRepository permissionJpaRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public PermissionModel save(PermissionModel model) {
        Permission entity = permissionMapper.modelToEntity(model);
        Permission savedEntity = permissionJpaRepository.save(entity);

        return permissionMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<PermissionModel> findById(Integer permissionId) {
        return permissionJpaRepository.findById(permissionId)
                .map(permissionMapper::entityToModel);
    }

    @Override
    public List<PermissionModel> findByName(String name, int page, int size) {
        return permissionJpaRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(permissionMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionModel> findAll(int page, int size) {
        return permissionJpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(permissionMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer permissionId) {
        this.findById(permissionId);
        permissionJpaRepository.deleteById(permissionId);
    }

    @Override
    public boolean existsByName(String name) {
        return permissionJpaRepository.existsByName(name);
    }
}

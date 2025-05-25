package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RoleRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RoleJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.Role;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleJpaAdapter implements RoleRepositoryPort {
    private final RoleJpaRepository roleJpaRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleModel save(RoleModel roleModel) {
        Role entity = roleMapper.modelToEntity(roleModel);
        Role savedEntity = roleJpaRepository.save(entity);

        return roleMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<RoleModel> findById(Integer roleId) {
        return roleJpaRepository.findById(roleId)
                .map(roleMapper::entityToModel);
    }

    @Override
    public void deleteById(Integer roleId) {
        this.findById(roleId);
        roleJpaRepository.deleteById(roleId);
    }

    @Override
    public Optional<RoleModel> findByName(String name) {
        return roleJpaRepository.findByName(name)
                .map(roleMapper::entityToModel);
    }

    @Override
    public List<RoleModel> findAll(int page, int size) {
        return roleJpaRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(roleMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByName(String name) {
        return roleJpaRepository.existsByName(name);
    }
}

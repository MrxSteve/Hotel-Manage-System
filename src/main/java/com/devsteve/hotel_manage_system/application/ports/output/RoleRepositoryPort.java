package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
    RoleModel save(RoleModel roleModel);
    Optional<RoleModel> findById(Integer roleId);
    void deleteById(Integer roleId);
    Optional<RoleModel> findByName(String name);
    List<RoleModel> findAll(int page, int size);
    boolean existsByName(String name);
}

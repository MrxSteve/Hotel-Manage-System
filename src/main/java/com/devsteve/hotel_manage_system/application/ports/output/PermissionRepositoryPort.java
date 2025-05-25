package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;

import java.util.List;
import java.util.Optional;

public interface PermissionRepositoryPort {
    PermissionModel save(PermissionModel model);
    Optional<PermissionModel> findById(Integer permissionId);
    List<PermissionModel> findByName(String name, int page, int size);
    List<PermissionModel> findAll(int page, int size);
    void deleteById(Integer permissionId);
    boolean existsByName(String name);
}

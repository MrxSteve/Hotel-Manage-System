package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.UserRoleModel;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepositoryPort {
    UserRoleModel save(UserRoleModel userRoleModel);
    void deleteByUserIdAndRoleId(UUID userId, Integer roleId);
    List<UserRoleModel> findByRoleId(Integer roleId, int page, int size);
    boolean existsByUserIdAndRoleId(UUID userId, Integer roleId);
}

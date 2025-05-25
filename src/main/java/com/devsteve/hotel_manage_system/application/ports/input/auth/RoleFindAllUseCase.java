package com.devsteve.hotel_manage_system.application.ports.input.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;

import java.util.List;

public interface RoleFindAllUseCase {
    List<RoleModel> findAllRoles(int page, int size);
}

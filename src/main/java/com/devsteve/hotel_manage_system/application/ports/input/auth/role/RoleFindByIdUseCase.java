package com.devsteve.hotel_manage_system.application.ports.input.auth.role;

import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;

public interface RoleFindByIdUseCase {
    RoleModel findById(Integer roleId);
}

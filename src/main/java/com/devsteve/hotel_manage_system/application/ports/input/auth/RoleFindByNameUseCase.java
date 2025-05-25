package com.devsteve.hotel_manage_system.application.ports.input.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;

public interface RoleFindByNameUseCase {
    RoleModel findByName(String name);
}

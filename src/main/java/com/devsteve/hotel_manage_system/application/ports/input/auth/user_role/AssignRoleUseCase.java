package com.devsteve.hotel_manage_system.application.ports.input.auth.user_role;

import java.util.UUID;

public interface AssignRoleUseCase {
    void assignRole(UUID userId, Integer roleId);
}

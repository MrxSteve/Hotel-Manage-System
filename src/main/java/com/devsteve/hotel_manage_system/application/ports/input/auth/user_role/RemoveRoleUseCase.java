package com.devsteve.hotel_manage_system.application.ports.input.auth.user_role;

import java.util.UUID;

public interface RemoveRoleUseCase {
    void removeRole(UUID userId, Integer roleId);
}
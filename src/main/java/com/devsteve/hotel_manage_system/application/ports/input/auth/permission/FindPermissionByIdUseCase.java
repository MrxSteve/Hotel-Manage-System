package com.devsteve.hotel_manage_system.application.ports.input.auth.permission;

import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;

public interface FindPermissionByIdUseCase {
    PermissionModel findById(Integer permissionId);
}

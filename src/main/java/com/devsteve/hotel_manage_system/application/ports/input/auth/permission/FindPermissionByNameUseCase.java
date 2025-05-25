package com.devsteve.hotel_manage_system.application.ports.input.auth.permission;

import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;

import java.util.List;

public interface FindPermissionByNameUseCase {
    List<PermissionModel> findByName(String name, int page, int size);
}

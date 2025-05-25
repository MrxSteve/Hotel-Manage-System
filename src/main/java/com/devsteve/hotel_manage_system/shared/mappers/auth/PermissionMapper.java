package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.infra.entities.Permission;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.PermissionRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    // DTO <-> Model
    PermissionModel requestToModel(PermissionRequest request);
    PermissionResponse modelToResponse(PermissionModel model);

    // Entity <-> Model
    PermissionModel entityToModel(Permission entity);
    Permission modelToEntity(PermissionModel model);
}

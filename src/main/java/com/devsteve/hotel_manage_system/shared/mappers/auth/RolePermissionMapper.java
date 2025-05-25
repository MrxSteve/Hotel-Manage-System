package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.domain.models.auth.RolePermissionIdModel;
import com.devsteve.hotel_manage_system.domain.models.auth.RolePermissionModel;
import com.devsteve.hotel_manage_system.infra.entities.RolePermission;
import com.devsteve.hotel_manage_system.infra.entities.RolePermissionId;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, PermissionMapper.class})
public interface RolePermissionMapper {
    // Entity <-> Model
    RolePermissionModel entityToModel(RolePermission entity);
    RolePermission modelToEntity(RolePermissionModel model);

    RolePermissionIdModel toModelId(RolePermissionId id);
    RolePermissionId toEntityId(RolePermissionIdModel idModel);

    // Model <-> DTO
    PermissionResponse modelToResponse(PermissionModel model);
}

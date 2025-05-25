package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.infra.entities.Role;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.RoleRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.RoleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    // DTO <-> Model
    RoleModel dtoToModel(RoleRequest request);
    RoleResponse modelToResponse(RoleModel model);

    // Entity <-> Model
    Role modelToEntity(RoleModel model);
    RoleModel entityToModel(Role entity);
}

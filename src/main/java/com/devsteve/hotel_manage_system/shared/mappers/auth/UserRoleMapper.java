package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserRoleIdModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserRoleModel;
import com.devsteve.hotel_manage_system.infra.entities.UserRole;
import com.devsteve.hotel_manage_system.infra.entities.UserRoleId;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, RoleMapper.class})
public interface UserRoleMapper {
    // Entity <-> Model
    UserRoleModel entityToModel(UserRole entity);
    UserRole modelToEntity(UserRoleModel model);

    UserRoleIdModel toModelId(UserRoleId id);
    UserRoleId toEntityId(UserRoleIdModel idModel);

    // Model <-> DTO
    UserResponse modelToResponse(UserModel model);
}

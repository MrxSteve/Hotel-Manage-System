package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.infra.entities.User;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UpdateUserRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UserRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserProfileMapper.class, RoleMapper.class})
public interface UserMapper {
    // Model <-> Entity
    @Mapping(target = "profile", source = "profile")
    UserModel entityToModel(User entity);

    @Mapping(target = "profile", source = "profile")
    User modelToEntity(UserModel model);

    // Request -> Model
    UserModel requestToModel(UserRequest request);

    // Update Request -> Model (actualización parcial)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromRequest(UpdateUserRequest request, @MappingTarget UserModel model);

    // Model -> Response
    @Mapping(target = "profile", source = "profile")
    @Mapping(target = "roles", source = "roles")
    UserResponse modelToResponse(UserModel model);
}

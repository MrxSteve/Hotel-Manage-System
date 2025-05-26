package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.infra.entities.User;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UpdateUserRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UserRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Model <-> Entity
    UserModel entityToModel(User entity);
    User modelToEntity(UserModel model);

    // Request -> Model
    UserModel requestToModel(UserRequest request);

    // Update Request -> Model (actualización parcial)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromRequest(UpdateUserRequest request, @MappingTarget UserModel model);

    // Model -> Response
    UserResponse modelToResponse(UserModel model);
}

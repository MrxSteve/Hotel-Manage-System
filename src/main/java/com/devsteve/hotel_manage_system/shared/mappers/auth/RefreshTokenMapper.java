package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;
import com.devsteve.hotel_manage_system.infra.entities.RefreshToken;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.GenerateRefreshTokenRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.RefreshTokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {
    // Entity <-> Model
    @Mapping(target = "userId", source = "user.id")
    RefreshTokenModel entityToModel(RefreshToken entity);

    @Mapping(target = "user.id", source = "userId")
    RefreshToken modelToEntity(RefreshTokenModel model);

    // Model <-> DTO
    RefreshTokenResponse modelToResponse(RefreshTokenModel model);

    RefreshTokenModel requestToModel(GenerateRefreshTokenRequest request);
}

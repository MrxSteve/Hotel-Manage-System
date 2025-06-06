package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;
import com.devsteve.hotel_manage_system.infra.entities.UserProfile;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UpdateUserProfileRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UserProfileRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserProfileResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    // DTO -> MODEL
    @Mapping(target = "id", ignore = true)
    UserProfileModel requestToModel(UserProfileRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRequestToModel(UpdateUserProfileRequest request, @MappingTarget UserProfileModel model);

    // MODEL -> DTO
    @Mapping(target = "userId", source = "userId")
    UserProfileResponse modelToResponse(UserProfileModel model);

    // ENTITY -> MODEL
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "id", source = "id")
    UserProfileModel entityToModel(UserProfile entity);

    // MODEL -> ENTITY
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", source = "id")
    UserProfile modelToEntity(UserProfileModel model);
}

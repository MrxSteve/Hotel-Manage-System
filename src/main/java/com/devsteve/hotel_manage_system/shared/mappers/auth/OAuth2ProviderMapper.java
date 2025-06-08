package com.devsteve.hotel_manage_system.shared.mappers.auth;

import com.devsteve.hotel_manage_system.domain.models.auth.OAuth2ProviderModel;
import com.devsteve.hotel_manage_system.infra.entities.Oauth2Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OAuth2ProviderMapper {
    @Mapping(target = "userId", source = "user.id")
    OAuth2ProviderModel entityToModel(Oauth2Provider entity);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "user", ignore = true)
    Oauth2Provider modelToEntity(OAuth2ProviderModel model);
}

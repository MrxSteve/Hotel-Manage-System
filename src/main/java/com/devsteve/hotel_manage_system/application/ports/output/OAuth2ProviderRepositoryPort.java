package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.OAuth2ProviderModel;

import java.util.Optional;
import java.util.UUID;

public interface OAuth2ProviderRepositoryPort {
    Optional<OAuth2ProviderModel> findByProviderAndProviderUserId(String provider, String providerUserId);

    Optional<OAuth2ProviderModel> findByUserId(UUID userId);

    OAuth2ProviderModel save(OAuth2ProviderModel model);
}

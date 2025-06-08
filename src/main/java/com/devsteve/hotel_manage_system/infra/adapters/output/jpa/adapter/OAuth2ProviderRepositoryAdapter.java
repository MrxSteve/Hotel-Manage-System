package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.OAuth2ProviderRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.OAuth2ProviderModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.OAuth2ProviderJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.Oauth2Provider;
import com.devsteve.hotel_manage_system.infra.entities.User;
import com.devsteve.hotel_manage_system.shared.mappers.auth.OAuth2ProviderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OAuth2ProviderRepositoryAdapter implements OAuth2ProviderRepositoryPort {
    private final OAuth2ProviderJpaRepository oAuth2ProviderJpaRepository;
    @Qualifier("OAuth2ProviderMapper")
    private final OAuth2ProviderMapper oAuth2ProviderMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<OAuth2ProviderModel> findByProviderAndProviderUserId(String provider, String providerUserId) {
        return oAuth2ProviderJpaRepository
                .findByProviderAndProviderUserId(provider, providerUserId)
                .map(oAuth2ProviderMapper::entityToModel);
    }

    @Override
    public Optional<OAuth2ProviderModel> findByUserId(UUID userId) {
        return oAuth2ProviderJpaRepository
                .findByUser_Id(userId)
                .map(oAuth2ProviderMapper::entityToModel);
    }

    @Override
    public OAuth2ProviderModel save(OAuth2ProviderModel model) {
        Oauth2Provider entity = oAuth2ProviderMapper.modelToEntity(model);

        User user = userJpaRepository.findById(model.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + model.getUserId()));

        entity.setUser(user);

        Oauth2Provider saved = oAuth2ProviderJpaRepository.save(entity);
        return oAuth2ProviderMapper.entityToModel(saved);
    }
}

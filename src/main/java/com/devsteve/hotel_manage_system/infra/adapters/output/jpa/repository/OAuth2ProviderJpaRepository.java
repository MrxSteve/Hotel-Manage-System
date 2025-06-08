package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.Oauth2Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OAuth2ProviderJpaRepository extends JpaRepository<Oauth2Provider, Integer> {
    Optional<Oauth2Provider> findByProviderAndProviderUserId(String provider, String providerUserId);

    Optional<Oauth2Provider> findByUser_Id(UUID userId);
}

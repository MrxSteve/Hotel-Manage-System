package com.devsteve.hotel_manage_system.domain.models.auth;

import java.util.UUID;

public class OAuth2ProviderModel {
    private Integer id;
    private UUID userId;
    private String provider;
    private String providerUserId;

    public OAuth2ProviderModel() {
    }

    public OAuth2ProviderModel(Integer id, UUID userId, String provider, String providerUserId) {
        this.id = id;
        this.userId = userId;
        this.provider = provider;
        this.providerUserId = providerUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
}

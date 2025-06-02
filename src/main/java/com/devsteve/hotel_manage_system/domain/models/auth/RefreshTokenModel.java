package com.devsteve.hotel_manage_system.domain.models.auth;

import java.time.Instant;
import java.util.UUID;

public class RefreshTokenModel {
    private Integer id;
    private UUID userId;
    private String token;
    private Instant expirationDate;

    public RefreshTokenModel() {
    }

    public RefreshTokenModel(Integer id, UUID userId, String token, Instant expirationDate) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.expirationDate = expirationDate;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }
}

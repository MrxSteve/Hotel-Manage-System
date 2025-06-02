package com.devsteve.hotel_manage_system.application.ports.input.auth.refresh;

import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;

import java.time.Instant;
import java.util.UUID;

public interface CreateRefreshTokenUseCase {
    RefreshTokenModel createToken(UUID userId, String token, Instant expirationDate);
}

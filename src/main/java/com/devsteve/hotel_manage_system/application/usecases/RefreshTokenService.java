package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.CreateRefreshTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.GetRefreshTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.RevokeTokenByUserUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.RevokeTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.RefreshTokenRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;

import java.time.Instant;
import java.util.UUID;

public class RefreshTokenService implements
        CreateRefreshTokenUseCase,
        GetRefreshTokenUseCase,
        RevokeTokenUseCase,
        RevokeTokenByUserUseCase {
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public RefreshTokenService(RefreshTokenRepositoryPort refreshTokenRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.refreshTokenRepositoryPort = refreshTokenRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public RefreshTokenModel createToken(UUID userId, String token, Instant expirationDate) {
        if (refreshTokenRepositoryPort.existsByToken(token)) {
            throw new IllegalArgumentException("Refresh token already exists: " + token);
        }

        if (!userRepositoryPort.findById(userId).isPresent()) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist.");
        }

        RefreshTokenModel model = new RefreshTokenModel();
        model.setUserId(userId);
        model.setToken(token);
        model.setExpirationDate(expirationDate);

        return refreshTokenRepositoryPort.save(model);
    }

    @Override
    public RefreshTokenModel getByToken(String token) {
        return refreshTokenRepositoryPort.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found: " + token));
    }

    @Override
    public void revokeToken(Integer id) {
        RefreshTokenModel token = refreshTokenRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found with id: " + id));
        refreshTokenRepositoryPort.deleteById(token.getId());
    }

    @Override
    public void revokeTokensByUserId(UUID userId) {
        if (!userRepositoryPort.findById(userId).isPresent()) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist.");
        }
        refreshTokenRepositoryPort.deleteByUserId(userId);
    }
}

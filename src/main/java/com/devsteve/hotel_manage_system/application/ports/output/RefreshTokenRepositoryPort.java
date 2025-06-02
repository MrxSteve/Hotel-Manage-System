package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepositoryPort {
    RefreshTokenModel save(RefreshTokenModel model);
    Optional<RefreshTokenModel> findByToken(String token);
    Optional<RefreshTokenModel> findById(Integer id);
    void deleteById(Integer id);
    void deleteByUserId(UUID userId);
    boolean existsByToken(String token);
}

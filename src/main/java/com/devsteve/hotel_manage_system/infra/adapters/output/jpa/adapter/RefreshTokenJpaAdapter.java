package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.RefreshTokenRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.RefreshTokenJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.RefreshToken;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RefreshTokenJpaAdapter implements RefreshTokenRepositoryPort {
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshTokenModel save(RefreshTokenModel model) {
        RefreshToken entity = refreshTokenMapper.modelToEntity(model);
        RefreshToken savedEntity = refreshTokenJpaRepository.save(entity);

        return refreshTokenMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<RefreshTokenModel> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token)
                .map(refreshTokenMapper::entityToModel);
    }

    @Override
    public Optional<RefreshTokenModel> findById(Integer id) {
        return refreshTokenJpaRepository.findById(id)
                .map(refreshTokenMapper::entityToModel);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);
        refreshTokenJpaRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        refreshTokenJpaRepository.deleteByUserId(userId);
    }

    @Override
    public boolean existsByToken(String token) {
        return refreshTokenJpaRepository.existsByToken(token);
    }
}

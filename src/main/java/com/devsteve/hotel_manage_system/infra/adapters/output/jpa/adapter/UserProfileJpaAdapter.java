package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.UserProfileRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserJpaRepository;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.UserProfileJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.User;
import com.devsteve.hotel_manage_system.infra.entities.UserProfile;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProfileJpaAdapter implements UserProfileRepositoryPort {
    private final UserProfileJpaRepository userProfileJpaRepository;
    private final UserProfileMapper userProfileMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserProfileModel save(UserProfileModel model) {
        User user = userJpaRepository.findById(model.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + model.getUserId()));

        UserProfile entity = userProfileMapper.modelToEntity(model);
        entity.setUser(user);

        UserProfile saved = userProfileJpaRepository.save(entity);

        return userProfileMapper.entityToModel(saved);
    }

    @Override
    public Optional<UserProfileModel> findById(UUID userId) {
        return userProfileJpaRepository.findById(userId)
                .map(userProfileMapper::entityToModel);
    }

    @Override
    public boolean existsByDui(String dui) {
        return userProfileJpaRepository.existsByDui(dui);
    }
}

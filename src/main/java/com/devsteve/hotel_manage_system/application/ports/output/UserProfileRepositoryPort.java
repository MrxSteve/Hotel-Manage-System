package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepositoryPort {
    UserProfileModel save(UserProfileModel model);
    Optional<UserProfileModel> findById(UUID userId);
    boolean existsByDui(String dui);
    Optional<UserProfileModel> findByUserId(UUID userId);
}

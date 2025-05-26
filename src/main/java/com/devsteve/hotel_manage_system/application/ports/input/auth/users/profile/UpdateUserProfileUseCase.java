package com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile;

import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;

import java.util.UUID;

public interface UpdateUserProfileUseCase {
    UserProfileModel update(UUID userId, UserProfileModel model);
}

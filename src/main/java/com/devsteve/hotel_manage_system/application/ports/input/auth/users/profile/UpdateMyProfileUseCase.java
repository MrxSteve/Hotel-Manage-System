package com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile;

import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;

public interface UpdateMyProfileUseCase {
    UserProfileModel updateMyProfile(UserProfileModel profile);
}

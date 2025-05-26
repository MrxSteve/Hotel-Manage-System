package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

import java.util.UUID;

public interface GetUserDetailsUseCase {
    UserModel getUserById(UUID userId);
}

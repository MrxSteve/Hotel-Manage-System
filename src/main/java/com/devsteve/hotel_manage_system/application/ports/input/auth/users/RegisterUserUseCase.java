package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

public interface RegisterUserUseCase {
    UserModel register(UserModel userModel);
}

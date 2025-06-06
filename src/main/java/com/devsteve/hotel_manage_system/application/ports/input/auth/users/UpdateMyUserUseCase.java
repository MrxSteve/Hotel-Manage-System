package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

public interface UpdateMyUserUseCase {
    UserModel updateMyUser(UserModel user);
}

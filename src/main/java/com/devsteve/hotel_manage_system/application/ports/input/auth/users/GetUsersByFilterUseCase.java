package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

import java.util.List;
import java.util.Optional;

public interface GetUsersByFilterUseCase {
    List<UserModel> getUsersByFilter(
            Optional<String> username,
            Optional<String> email,
            Optional<Boolean> enabled,
            Optional<Boolean> mustChangePassword,
            int page,
            int size);
}

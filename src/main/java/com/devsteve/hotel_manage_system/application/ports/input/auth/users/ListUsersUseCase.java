package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

import java.util.List;

public interface ListUsersUseCase {
    List<UserModel> getAllUsers(int page, int size);
}

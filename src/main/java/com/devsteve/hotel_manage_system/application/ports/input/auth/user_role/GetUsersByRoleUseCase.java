package com.devsteve.hotel_manage_system.application.ports.input.auth.user_role;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

import java.util.List;

public interface GetUsersByRoleUseCase {
    List<UserModel> getUsersByRole(Integer roleId, int page, int size);
}

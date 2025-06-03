package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.AssignRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.GetUsersByRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.RemoveRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.RoleRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRoleRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserRoleIdModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserRoleModel;

import java.util.List;
import java.util.UUID;

public class UserRoleService implements
        AssignRoleUseCase,
        RemoveRoleUseCase,
        GetUsersByRoleUseCase {
    private final UserRoleRepositoryPort userRoleRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;

    public UserRoleService(UserRoleRepositoryPort userRoleRepositoryPort, UserRepositoryPort userRepositoryPort, RoleRepositoryPort roleRepositoryPort) {
        this.userRoleRepositoryPort = userRoleRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.roleRepositoryPort = roleRepositoryPort;
    }

    @Override
    public void assignRole(UUID userId, Integer roleId) {
        this.validateUserAndRoleExist(userId, roleId);

        if (userRoleRepositoryPort.existsByUserIdAndRoleId(userId, roleId)) {
            throw new IllegalArgumentException("Role " + roleId + " is already assigned to user " + userId);
        }

        UserRoleIdModel id = new UserRoleIdModel(userId, roleId);

        UserModel user = new UserModel();
        user.setId(userId);

        RoleModel role = new RoleModel();
        role.setId(roleId);

        UserRoleModel userRole = new UserRoleModel(id, user, role);
        userRoleRepositoryPort.save(userRole);
    }

    @Override
    public void removeRole(UUID userId, Integer roleId) {
        this.validateUserAndRoleExist(userId, roleId);

        if (!userRoleRepositoryPort.existsByUserIdAndRoleId(userId, roleId)) {
            throw new IllegalArgumentException("Role " + roleId + " is not assigned to user " + userId);
        }

        userRoleRepositoryPort.deleteByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public List<UserModel> getUsersByRole(Integer roleId, int page, int size) {
        return userRoleRepositoryPort.findByRoleId(roleId, page, size);
    }

    private void validateUserAndRoleExist(UUID userId, Integer roleId) {
        if (!userRepositoryPort.findById(userId).isPresent()) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist.");
        }

        if (!roleRepositoryPort.findById(roleId).isPresent()) {
            throw new IllegalArgumentException("Role with id " + roleId + " does not exist.");
        }
    }
}
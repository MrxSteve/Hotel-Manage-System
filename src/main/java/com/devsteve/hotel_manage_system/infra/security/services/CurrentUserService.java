package com.devsteve.hotel_manage_system.infra.security.services;

import com.devsteve.hotel_manage_system.application.ports.output.PasswordEncoderPort;
import com.devsteve.hotel_manage_system.application.ports.output.RolePermissionRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.domain.models.auth.RolePermissionModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final RolePermissionRepositoryPort rolePermissionRepositoryPort;

    public UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();

        return userRepositoryPort.findByUsername(usernameOrEmail)
                .or(() -> userRepositoryPort.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new RuntimeException("Authenticated user not found: " + usernameOrEmail));
    }

    public void changeMyPassword(String currentPassword, String newPassword) {
        UserModel currentUser = getCurrentUser();

        if (!passwordEncoderPort.matches(currentPassword, currentUser.getPassword())) {
            throw new RuntimeException("Current password is incorrect.");
        }

        currentUser.setPassword(passwordEncoderPort.encode(newPassword));

        currentUser.setMustChangePassword(false);

        userRepositoryPort.save(currentUser);
    }

    public List<PermissionModel> getMyPermissions() {
        UserModel currentUser = getCurrentUser();

        return currentUser.getRoles().stream()
                .flatMap(role -> rolePermissionRepositoryPort
                        .findByRoleId(role.getId(), 0, Integer.MAX_VALUE).stream())
                .map(RolePermissionModel::getPermission)
                .distinct()
                .toList();
    }
}

package com.devsteve.hotel_manage_system.infra.security.services;

import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final UserRepositoryPort userRepositoryPort;

    public UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();

        return userRepositoryPort.findByUsername(usernameOrEmail)
                .or(() -> userRepositoryPort.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new RuntimeException("Authenticated user not found: " + usernameOrEmail));
    }
}

package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.infra.security.dto.req.LoginRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.req.RefreshTokenRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.res.LoginResponse;
import com.devsteve.hotel_manage_system.infra.security.services.AuthService;
import com.devsteve.hotel_manage_system.infra.security.services.CurrentUserService;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CurrentUserService currentUserService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.authenticateUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshAccessToken(request.getRefreshToken()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        UserModel user = currentUserService.getCurrentUser();
        UserResponse response = userMapper.modelToResponse(user);
        return ResponseEntity.ok(response);
    }
}

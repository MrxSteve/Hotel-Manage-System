package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.users.UpdateMyUserUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.UpdateUserUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.UpdateMyProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.UpdateUserProfileUseCase;
import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;
import com.devsteve.hotel_manage_system.infra.security.dto.req.ChangeMyPasswordRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.req.LoginRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.req.LogoutRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.req.RefreshTokenRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.res.LoginResponse;
import com.devsteve.hotel_manage_system.infra.security.services.AuthService;
import com.devsteve.hotel_manage_system.infra.security.services.CurrentUserService;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UpdateUserProfileRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UpdateUserRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.PermissionResponse;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserProfileResponse;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.PermissionMapper;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserMapper;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserProfileMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CurrentUserService currentUserService;
    private final UserMapper userMapper;
    private final UpdateMyUserUseCase updateMyUserUseCase;
    private final UpdateMyProfileUseCase updateMyProfileUseCase;
    private final UserProfileMapper userProfileMapper;
    private final PermissionMapper permissionMapper;

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

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateMyUser(@Valid @RequestBody UpdateUserRequest request) {
        UserModel currentUser = currentUserService.getCurrentUser();

        userMapper.updateModelFromRequest(request, currentUser);

        UserModel updatedUser = updateMyUserUseCase.updateMyUser(currentUser);

        return ResponseEntity.ok(userMapper.modelToResponse(updatedUser));
    }

    @PutMapping("/me/profile")
    public ResponseEntity<UserProfileResponse> updateMyProfile(@Valid @RequestBody UpdateUserProfileRequest request) {
        UserModel currentUser = currentUserService.getCurrentUser();

        UserProfileModel profile = currentUser.getProfile();
        if (profile == null) {
            throw new RuntimeException("User has no profile");
        }

        userProfileMapper.updateRequestToModel(request, profile);

        UserProfileModel updatedProfile = updateMyProfileUseCase.updateMyProfile(profile);

        return ResponseEntity.ok(userProfileMapper.modelToResponse(updatedProfile));
    }

    @PutMapping("/me/password")
    public ResponseEntity<Void> changeMyPassword(@Valid @RequestBody ChangeMyPasswordRequest request) {
        currentUserService.changeMyPassword(request.getCurrentPassword(), request.getNewPassword());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest request) {

        authService.logout(request);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me/must-change-password")
    public ResponseEntity<Boolean> mustChangePassword() {
        UserModel currentUser = currentUserService.getCurrentUser();
        return ResponseEntity.ok(currentUser.getMustChangePassword());
    }

    @GetMapping("/me/permissions")
    public ResponseEntity<List<PermissionResponse>> getMyPermissions() {
        List<PermissionModel> models = currentUserService.getMyPermissions();

        List<PermissionResponse> responses = models.stream()
                .map(permissionMapper::modelToResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

}

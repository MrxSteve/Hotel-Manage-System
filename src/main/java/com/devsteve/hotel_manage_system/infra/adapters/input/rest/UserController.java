package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.users.*;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.ChangePasswordRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UpdateUserRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UserRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserDetailsUseCase getUserDetailsUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final EnableDisableUserUseCase enableDisableUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final UpdatePasswordStatusUseCase updatePasswordStatusUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;
    private final GetUsersByFilterUseCase getUsersByFilterUseCase;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        UserModel user = registerUserUseCase.register(userMapper.requestToModel(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.modelToResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                userMapper.modelToResponse(getUserDetailsUseCase.getUserById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<UserModel> users = listUsersUseCase.getAllUsers(page, size);

        return ResponseEntity.ok(
                users.stream()
                .map(userMapper::modelToResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> filter(
            @RequestParam Optional<String> username,
            @RequestParam Optional<String> email,
            @RequestParam Optional<Boolean> enabled,
            @RequestParam Optional<Boolean> mustChangePassword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<UserModel> filtered = getUsersByFilterUseCase.getUsersByFilter(username, email, enabled, mustChangePassword, page, size);
        return ResponseEntity.ok(filtered.stream().map(userMapper::modelToResponse).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id,
                                               @Valid @RequestBody UpdateUserRequest request) {
        UserModel existing = getUserDetailsUseCase.getUserById(id);
        userMapper.updateModelFromRequest(request, existing);
        UserModel updated = updateUserUseCase.updateUser(id, existing);
        return ResponseEntity.ok(userMapper.modelToResponse(updated));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable UUID id,
                                               @Valid @RequestBody ChangePasswordRequest request) {
        changePasswordUseCase.changePassword(id, request.getNewPassword());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/password-status")
    public ResponseEntity<Void> markPasswordChanged(@PathVariable UUID id,
                                                    @RequestParam boolean mustChangePassword) {
        updatePasswordStatusUseCase.markPasswordAsChanged(id, mustChangePassword);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> toggleEnabled(@PathVariable UUID id,
                                              @RequestParam boolean enabled) {
        enableDisableUserUseCase.setUserEnabled(id, enabled);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUserByIdUseCase.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}

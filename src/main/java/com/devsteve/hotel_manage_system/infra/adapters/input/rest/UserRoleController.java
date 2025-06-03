package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.AssignRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.GetUsersByRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.RemoveRoleUseCase;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.AssignRoleToUserRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserMapper;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserRoleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles/{roleId}/users")
@RequiredArgsConstructor
public class UserRoleController {

    private final AssignRoleUseCase assignRoleUseCase;
    private final RemoveRoleUseCase removeRoleUseCase;
    private final GetUsersByRoleUseCase getUsersByRoleUseCase;
    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Void> assignRole(
            @PathVariable Integer roleId,
            @Valid @RequestBody AssignRoleToUserRequest request) {
        assignRoleUseCase.assignRole(request.getUserId(), roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeRole(
            @PathVariable UUID userId,
            @PathVariable Integer roleId) {
        removeRoleUseCase.removeRole(userId, roleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsersByRole(
            @PathVariable Integer roleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<UserModel> models = getUsersByRoleUseCase.getUsersByRole(roleId, page, size);
        List<UserResponse> responses = models.stream()
                .map(userMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}

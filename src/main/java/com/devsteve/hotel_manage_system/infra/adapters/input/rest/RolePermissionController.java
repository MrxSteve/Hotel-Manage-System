package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.AssignPermissionToRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.GetPermissionsByRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.RemovePermissionToRoleUseCase;
import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.AssignPermissionToRoleRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.PermissionResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RolePermissionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles/{roleId}/permissions")
@RequiredArgsConstructor
public class RolePermissionController {
    private final AssignPermissionToRoleUseCase assignPermissionToRoleUseCase;
    private final RemovePermissionToRoleUseCase removePermissionToRoleUseCase;
    private final GetPermissionsByRoleUseCase getPermissionsByRoleUseCase;
    private final RolePermissionMapper rolePermissionMapper;

    @PostMapping
    public ResponseEntity<Void> assignPermission(
            @PathVariable Integer roleId,
            @Valid @RequestBody AssignPermissionToRoleRequest request) {
        assignPermissionToRoleUseCase.assignPermissionToRole(roleId, request.getPermissionId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> removePermission(
            @PathVariable Integer roleId,
            @PathVariable Integer permissionId) {
        removePermissionToRoleUseCase.removePermissionToRole(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> getPermissionsByRole(
            @PathVariable Integer roleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<PermissionModel> models = getPermissionsByRoleUseCase.getPermissionsByRole(roleId, page, size);
        List<PermissionResponse> responses = models.stream()
                .map(rolePermissionMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}

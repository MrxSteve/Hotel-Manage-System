package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.permission.*;
import com.devsteve.hotel_manage_system.domain.models.auth.PermissionModel;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.PermissionRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.PermissionResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.PermissionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final CreatePermissionUseCase createPermissionUseCase;
    private final FindPermissionByIdUseCase findPermissionByIdUseCase;
    private final FindAllPermissionUseCase findAllPermissionUseCase;
    private final FindPermissionByNameUseCase findPermissionByNameUseCase;
    private final DeletePermissionUseCase deletePermissionUseCase;
    private final PermissionMapper permissionMapper;

    @PostMapping
    public ResponseEntity<PermissionResponse> create(@Valid @RequestBody PermissionRequest request) {
        PermissionModel model = permissionMapper.requestToModel(request);
        PermissionModel created = createPermissionUseCase.createPermission(model);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(permissionMapper.modelToResponse(created));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<PermissionModel> models = findAllPermissionUseCase.findAll(page, size);
        List<PermissionResponse> responses = models.stream()
                .map(permissionMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponse> findById(@PathVariable Integer id) {
        PermissionModel model = findPermissionByIdUseCase.findById(id);
        return ResponseEntity.ok(permissionMapper.modelToResponse(model));
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<PermissionResponse>> findByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<PermissionModel> models = findPermissionByNameUseCase.findByName(name, page, size);
        List<PermissionResponse> responses = models.stream()
                .map(permissionMapper::modelToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deletePermissionUseCase.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}

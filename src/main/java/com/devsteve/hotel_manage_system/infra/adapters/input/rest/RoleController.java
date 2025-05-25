package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.*;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.RoleRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.RoleResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RoleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleCreateUseCase roleCreateUseCase;
    private final RoleFindAllUseCase roleFindAllUseCase;
    private final RoleFindByIdUseCase roleFindByIdUseCase;
    private final RoleDeleteUseCase roleDeleteUseCase;
    private final RoleFindByNameUseCase roleFindByNameUseCase;
    private final RoleMapper roleMapper;

    @PostMapping
    public ResponseEntity<RoleResponse> create(@Valid @RequestBody RoleRequest request) {
        RoleModel model = roleMapper.dtoToModel(request);
        RoleModel created = roleCreateUseCase.createRole(model);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleMapper.modelToResponse(created));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<RoleModel> roles = roleFindAllUseCase.findAllRoles(page, size);
        List<RoleResponse> responses = roles.stream()
                .map(roleMapper::modelToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> findById(@PathVariable Integer id) {
        RoleModel model = roleFindByIdUseCase.findById(id);
        return ResponseEntity.ok(roleMapper.modelToResponse(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roleDeleteUseCase.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<RoleResponse> findByName(@RequestParam String name) {
        RoleModel model = roleFindByNameUseCase.findByName(name);
        return ResponseEntity.ok(roleMapper.modelToResponse(model));
    }
}

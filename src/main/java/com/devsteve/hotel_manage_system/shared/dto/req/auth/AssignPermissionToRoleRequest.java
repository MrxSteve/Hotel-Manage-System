package com.devsteve.hotel_manage_system.shared.dto.req.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AssignPermissionToRoleRequest {
    @NotNull(message = "Permission ID cannot be null")
    private Integer permissionId;
}

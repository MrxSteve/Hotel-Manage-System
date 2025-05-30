package com.devsteve.hotel_manage_system.shared.dto.req.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AssignRoleToUserRequest {
    @NotNull(message = "Role ID is required")
    private Integer roleId;
}

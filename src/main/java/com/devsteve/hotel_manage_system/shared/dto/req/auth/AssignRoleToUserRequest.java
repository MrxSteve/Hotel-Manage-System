package com.devsteve.hotel_manage_system.shared.dto.req.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AssignRoleToUserRequest {
    @NotNull(message = "User ID is required")
    private UUID userId;
}

package com.devsteve.hotel_manage_system.shared.dto.req.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GenerateRefreshTokenRequest {
    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotBlank(message = "Token cannot be blank")
    private String token;

    @NotNull(message = "Expiration date cannot be null")
    private Instant expirationDate;
}

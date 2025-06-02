package com.devsteve.hotel_manage_system.shared.dto.res.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RefreshTokenResponse {
    private Integer id;
    private UUID userId;
    private String token;
    private Instant expirationDate;
}

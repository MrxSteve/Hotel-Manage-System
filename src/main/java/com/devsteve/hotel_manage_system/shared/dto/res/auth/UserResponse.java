package com.devsteve.hotel_manage_system.shared.dto.res.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private String picture;
    private String pin;
    private Boolean enabled;
    private Boolean mustChangePassword;
    private Instant createdAt;
    private Instant updatedAt;
}

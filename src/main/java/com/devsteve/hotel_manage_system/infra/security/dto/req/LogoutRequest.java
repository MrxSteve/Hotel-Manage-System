package com.devsteve.hotel_manage_system.infra.security.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class LogoutRequest {
    @NotBlank(message = "Refresh token is required")
    private String refreshToken;
}

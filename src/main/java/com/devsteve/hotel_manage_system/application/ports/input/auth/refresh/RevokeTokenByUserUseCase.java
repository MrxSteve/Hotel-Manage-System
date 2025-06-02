package com.devsteve.hotel_manage_system.application.ports.input.auth.refresh;

import java.util.UUID;

public interface RevokeTokenByUserUseCase {
    void revokeTokensByUserId(UUID userId);
}

package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import java.util.UUID;

public interface UpdatePasswordStatusUseCase {
    void markPasswordAsChanged(UUID userId, boolean mustChangePassword);
}

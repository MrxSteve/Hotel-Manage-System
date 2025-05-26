package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import java.util.UUID;

public interface EnableDisableUserUseCase {
    void setUserEnabled(UUID userId, boolean enabled);
}

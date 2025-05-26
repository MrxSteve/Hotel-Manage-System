package com.devsteve.hotel_manage_system.application.ports.input.auth.users;

import java.util.UUID;

public interface ChangePasswordUseCase {
    void changePassword(UUID userId, String newPassword);
}

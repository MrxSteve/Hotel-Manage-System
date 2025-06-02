package com.devsteve.hotel_manage_system.application.ports.input.auth.refresh;

import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;

public interface GetRefreshTokenUseCase {
    RefreshTokenModel getByToken(String token);
}

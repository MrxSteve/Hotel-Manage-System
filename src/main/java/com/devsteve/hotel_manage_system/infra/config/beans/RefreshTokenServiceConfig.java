package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.CreateRefreshTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.GetRefreshTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.RevokeTokenByUserUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.RevokeTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.RefreshTokenRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RefreshTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefreshTokenServiceConfig {
    @Bean
    public RefreshTokenService refreshTokenService(
            RefreshTokenRepositoryPort refreshTokenRepositoryPort,
            UserRepositoryPort userRepositoryPort
    ) {
        return new RefreshTokenService(refreshTokenRepositoryPort, userRepositoryPort);
    }

    @Bean
    public CreateRefreshTokenUseCase createRefreshTokenUseCase(RefreshTokenService refreshTokenService) {
        return refreshTokenService;
    }

    @Bean
    public GetRefreshTokenUseCase getRefreshTokenUseCase(RefreshTokenService refreshTokenService) {
        return refreshTokenService;
    }

    @Bean
    public RevokeTokenUseCase revokeTokenUseCase(RefreshTokenService refreshTokenService) {
        return refreshTokenService;
    }

    @Bean
    public RevokeTokenByUserUseCase revokeTokenByUserUseCase(RefreshTokenService refreshTokenService) {
        return refreshTokenService;
    }
}

package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.CreateUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.GetUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.UpdateUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.UserProfileRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.UserProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserProfileServiceConfig {
    @Bean
    public UserProfileService userProfileService(UserProfileRepositoryPort userProfileRepositoryPort, UserRepositoryPort userRepositoryPort) {
        return new UserProfileService(userProfileRepositoryPort, userRepositoryPort);
    }

    @Bean
    public CreateUserProfileUseCase createUserProfileUseCase(UserProfileService userProfileService) {
        return userProfileService;
    }

    @Bean
    public UpdateUserProfileUseCase updateUserProfileUseCase(UserProfileService userProfileService) {
        return userProfileService;
    }

    @Bean
    public GetUserProfileUseCase getUserProfileUseCase(UserProfileService userProfileService) {
        return userProfileService;
    }
}

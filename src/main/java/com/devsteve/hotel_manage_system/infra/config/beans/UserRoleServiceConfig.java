package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.AssignRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.GetUsersByRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.RemoveRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.RoleRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRoleRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.UserRoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRoleServiceConfig {
    @Bean
    public UserRoleService userRoleService(
            UserRoleRepositoryPort userRoleRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            RoleRepositoryPort roleRepositoryPort
    ) {
        return new UserRoleService(
                userRoleRepositoryPort,
                userRepositoryPort,
                roleRepositoryPort
        );
    }

    @Bean
    public AssignRoleUseCase assignRoleUseCase(UserRoleService userRoleService) {
        return userRoleService;
    }

    @Bean
    public RemoveRoleUseCase removeRoleUseCase(UserRoleService userRoleService) {
        return userRoleService;
    }

    @Bean
    public GetUsersByRoleUseCase getUsersByRoleUseCase(UserRoleService userRoleService) {
        return userRoleService;
    }
}

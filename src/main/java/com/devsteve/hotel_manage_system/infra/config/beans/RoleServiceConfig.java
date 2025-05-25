package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.auth.role.*;
import com.devsteve.hotel_manage_system.application.ports.output.RoleRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleServiceConfig {
    @Bean
    public RoleService roleService(RoleRepositoryPort repository) {
        return new RoleService(repository);
    }

    @Bean
    public RoleCreateUseCase roleCreateUseCase(RoleService service) {
        return service;
    }

    @Bean
    public RoleFindByIdUseCase roleFindByIdUseCase(RoleService service) {
        return service;
    }

    @Bean
    public RoleFindAllUseCase roleFindAllUseCase(RoleService service) {
        return service;
    }

    @Bean
    public RoleDeleteUseCase roleDeleteUseCase(RoleService service) {
        return service;
    }

    @Bean
    public RoleFindByNameUseCase roleFindByNameUseCase(RoleService service) {
        return service;
    }
}

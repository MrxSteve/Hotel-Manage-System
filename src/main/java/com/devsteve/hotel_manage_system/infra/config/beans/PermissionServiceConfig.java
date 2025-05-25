package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.auth.permission.*;
import com.devsteve.hotel_manage_system.application.ports.output.PermissionRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.PermissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionServiceConfig {
    @Bean
    public PermissionService permissionService(PermissionRepositoryPort permissionRepositoryPort) {
        return new PermissionService(permissionRepositoryPort);
    }

    @Bean
    public CreatePermissionUseCase createPermissionUseCase(PermissionService permissionService) {
        return permissionService;
    }

    @Bean
    public FindPermissionByIdUseCase findPermissionByIdUseCase(PermissionService permissionService) {
        return permissionService;
    }

    @Bean
    public FindAllPermissionUseCase findAllPermissionUseCase(PermissionService permissionService) {
        return permissionService;
    }

    @Bean
    public FindPermissionByNameUseCase findPermissionByNameUseCase(PermissionService permissionService) {
        return permissionService;
    }

    @Bean
    public DeletePermissionUseCase deletePermissionUseCase(PermissionService permissionService) {
        return permissionService;
    }
}

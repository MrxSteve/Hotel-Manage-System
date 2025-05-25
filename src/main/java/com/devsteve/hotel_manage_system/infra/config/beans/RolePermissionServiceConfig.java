package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.AssignPermissionToRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.GetPermissionsByRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.role_permission.RemovePermissionToRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.PermissionRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RolePermissionRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.RoleRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.RolePermissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolePermissionServiceConfig {
    @Bean
    public RolePermissionService rolePermissionService(
            RolePermissionRepositoryPort rolePermissionRepositoryPort,
            RoleRepositoryPort roleRepositoryPort,
            PermissionRepositoryPort permissionRepositoryPort
    ) {
        return new RolePermissionService(
                rolePermissionRepositoryPort,
                roleRepositoryPort,
                permissionRepositoryPort
        );
    }

    @Bean
    public AssignPermissionToRoleUseCase assignPermissionToRoleUseCase(RolePermissionService rolePermissionService) {
        return rolePermissionService;
    }

    @Bean
    public RemovePermissionToRoleUseCase removePermissionToRoleUseCase(RolePermissionService rolePermissionService) {
        return rolePermissionService;
    }

    @Bean
    public GetPermissionsByRoleUseCase getPermissionsByRoleUseCase(RolePermissionService rolePermissionService) {
        return rolePermissionService;
    }
}

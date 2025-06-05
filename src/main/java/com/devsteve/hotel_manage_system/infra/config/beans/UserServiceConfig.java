package com.devsteve.hotel_manage_system.infra.config.beans;

import com.devsteve.hotel_manage_system.application.ports.input.auth.users.*;
import com.devsteve.hotel_manage_system.application.ports.output.PasswordEncoderPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.application.usecases.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {
    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        return new UserService(userRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public GetUserDetailsUseCase getUserDetailsUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public ListUsersUseCase listUsersUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public EnableDisableUserUseCase enableDisableUserUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public ChangePasswordUseCase changePasswordUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public UpdatePasswordStatusUseCase updatePasswordStatusUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public DeleteUserByIdUseCase deleteUserByIdUseCase(UserService userService) {
        return userService;
    }

    @Bean
    public GetUsersByFilterUseCase getUsersByFilterUseCase(UserService userService) {
        return userService;
    }
}

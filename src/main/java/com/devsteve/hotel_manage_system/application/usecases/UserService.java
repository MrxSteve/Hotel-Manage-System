package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.auth.users.*;
import com.devsteve.hotel_manage_system.application.ports.output.PasswordEncoderPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements
        RegisterUserUseCase,
        GetUserDetailsUseCase,
        ListUsersUseCase,
        EnableDisableUserUseCase,
        ChangePasswordUseCase,
        UpdatePasswordStatusUseCase,
        UpdateUserUseCase,
        DeleteUserByIdUseCase,
        GetUsersByFilterUseCase{
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public UserModel register(UserModel userModel) {
        if (userRepositoryPort.existsByEmail(userModel.getEmail())) {
            throw new IllegalArgumentException("Email " + userModel.getEmail() + " is already in use.");
        }

        if (userRepositoryPort.existsByUsername(userModel.getUsername())) {
            throw new IllegalArgumentException("Username " + userModel.getUsername() + " is already in use.");
        }

        userModel.setEnabled(true);
        userModel.setMustChangePassword(true);

        userModel.setPassword(passwordEncoderPort.encode(userModel.getPassword()));

        return userRepositoryPort.save(userModel);
    }

    @Override
    public UserModel getUserById(UUID userId) {
        return userRepositoryPort.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public List<UserModel> getAllUsers(int page, int size) {
        return userRepositoryPort.findAll(page, size);
    }

    @Override
    public void setUserEnabled(UUID userId, boolean enabled) {
        UserModel user = this.getUserById(userId);
        user.setEnabled(enabled);
        userRepositoryPort.save(user);
    }

    @Override
    public void changePassword(UUID userId, String newPassword) {
        UserModel user = this.getUserById(userId);
        user.setPassword(passwordEncoderPort.encode(newPassword));
        userRepositoryPort.save(user);
    }

    @Override
    public void markPasswordAsChanged(UUID userId, boolean mustChangePassword) {
        UserModel user = this.getUserById(userId);
        user.setMustChangePassword(mustChangePassword);
        userRepositoryPort.save(user);
    }

    @Override
    public UserModel updateUser(UUID userId, UserModel user) {
        UserModel existingUser = this.getUserById(userId);
        if (user.getUsername() != null &&
                userRepositoryPort.existsByUsername(user.getUsername()) &&
                !existingUser.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("Username " + user.getUsername() + " is already in use.");
        }

        if (user.getEmail() != null &&
                userRepositoryPort.existsByEmail(user.getEmail()) &&
                !existingUser.getEmail().equals(user.getEmail())) {
            throw new IllegalArgumentException("Email " + user.getEmail() + " is already in use.");
        }

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPicture() != null) {
            existingUser.setPicture(user.getPicture());
        }

        if (user.getPin() != null) {
            existingUser.setPin(user.getPin());
        }
        return userRepositoryPort.save(existingUser);
    }

    @Override
    public void deleteUserById(UUID userId) {
        this.getUserById(userId);
        userRepositoryPort.deleteById(userId);
    }

    @Override
    public List<UserModel> getUsersByFilter(
            Optional<String> username,
            Optional<String> email,
            Optional<Boolean> enabled,
            Optional<Boolean> mustChangePassword,
            int page,
            int size) {
        return userRepositoryPort.findByFilters(
                username,
                email,
                enabled,
                mustChangePassword,
                page,
                size);
    }
}

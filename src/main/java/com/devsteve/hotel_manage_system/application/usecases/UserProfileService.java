package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.CreateUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.GetUserProfileByUserIdUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.GetUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.UpdateUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.UserProfileRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;

import java.util.UUID;

public class UserProfileService implements
        CreateUserProfileUseCase,
        UpdateUserProfileUseCase,
        GetUserProfileUseCase,
        GetUserProfileByUserIdUseCase {
    private final UserProfileRepositoryPort userProfileRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    public UserProfileService(UserProfileRepositoryPort userProfileRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.userProfileRepositoryPort = userProfileRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserProfileModel create(UserProfileModel model) {
        this.userRepositoryPort.findById(model.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + model.getUserId()));

        if (userProfileRepositoryPort.findByUserId(model.getUserId()).isPresent()) {
            throw new IllegalArgumentException("User profile already exists for user ID: " + model.getUserId());
        }

        if (userProfileRepositoryPort.existsByDui(model.getDui())) {
            throw new IllegalArgumentException("User profile with Document " + model.getDui() + " already exists.");
        }

        return userProfileRepositoryPort.save(model);
    }

    @Override
    public UserProfileModel getProfileByUserId(UUID userId) {
        return userProfileRepositoryPort.findById(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found for user ID: " + userId));
    }

    @Override
    public UserProfileModel update(UUID userId, UserProfileModel model) {
        UserProfileModel existing = this.getProfileByUserId(userId);

        if (existing.getDui() != null &&
                userProfileRepositoryPort.existsByDui(model.getDui()) &&
                !existing.getDui().equals(model.getDui())) {
            throw new IllegalArgumentException("Document " + model.getDui() + " is already in use.");
        }

        if (model.getNombreCompleto() != null) {
            existing.setNombreCompleto(model.getNombreCompleto());
        }

        if (model.getDui() != null) {
            existing.setDui(model.getDui());
        }

        if (model.getTelefono() != null) {
            existing.setTelefono(model.getTelefono());
        }

        if (model.getDireccion() != null) {
            existing.setDireccion(model.getDireccion());
        }

        if (model.getFechaNacimiento() != null) {
            existing.setFechaNacimiento(model.getFechaNacimiento());
        }

        if (model.getGenero() != null) {
            existing.setGenero(model.getGenero());
        }

        if (model.getNacionalidad() != null) {
            existing.setNacionalidad(model.getNacionalidad());
        }

        return userProfileRepositoryPort.save(existing);
    }

    @Override
    public UserProfileModel getUserProfileByUserId(UUID userId) {
        return userProfileRepositoryPort.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user whit ID: " + userId));
    }
}

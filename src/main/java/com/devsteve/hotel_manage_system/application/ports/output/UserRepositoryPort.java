package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    UserModel save(UserModel model);
    Optional<UserModel> findById(UUID id);
    Optional<UserModel> findByEmail(String email);
    List<UserModel> findAll(int page, int size);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    void deleteById(UUID id);
    List<UserModel> findByFilters(
            Optional<String> username,
            Optional<String> email,
            Optional<Boolean> enabled,
            Optional<Boolean> mustChangePassword,
            int page,
            int size);
}

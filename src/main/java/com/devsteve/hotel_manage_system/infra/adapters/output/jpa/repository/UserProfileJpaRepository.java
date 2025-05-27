package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileJpaRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findById(UUID id);
    boolean existsByDui(String dui);
}

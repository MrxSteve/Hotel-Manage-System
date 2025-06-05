package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID>,
        JpaSpecificationExecutor<User> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    // Security
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}

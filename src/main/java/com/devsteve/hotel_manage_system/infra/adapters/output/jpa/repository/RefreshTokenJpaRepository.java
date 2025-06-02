package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findById(Integer id);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(UUID userId);
    boolean existsByToken(String token);
}

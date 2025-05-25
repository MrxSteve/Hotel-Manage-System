package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository  extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
    //Optional<Role> findById(Integer id);
    boolean existsByName(String name);
}

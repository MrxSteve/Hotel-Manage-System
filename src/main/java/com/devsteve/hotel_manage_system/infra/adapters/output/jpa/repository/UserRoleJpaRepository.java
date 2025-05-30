package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.UserRole;
import com.devsteve.hotel_manage_system.infra.entities.UserRoleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleJpaRepository extends JpaRepository<UserRole, UserRoleId> {
    boolean existsById(UserRoleId id);
    void deleteById(UserRoleId id);
    Page<UserRole> findByRole_Id(Integer roleId, Pageable pageable);
    List<UserRole> findByUser_Id(UUID userId);
}

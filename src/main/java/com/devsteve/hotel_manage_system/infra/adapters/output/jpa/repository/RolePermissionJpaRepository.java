package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.RolePermission;
import com.devsteve.hotel_manage_system.infra.entities.RolePermissionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionJpaRepository extends JpaRepository<RolePermission, RolePermissionId> {
    boolean existsById(RolePermissionId id);
    void deleteById(RolePermissionId id);
    Page<RolePermission> findByRole_Id(Integer roleId, Pageable pageable);
}

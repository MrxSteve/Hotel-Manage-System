package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository;

import com.devsteve.hotel_manage_system.infra.entities.ExtraService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtraServiceJpaRepository extends JpaRepository<ExtraService, Integer> {
    List<ExtraService> findAllByActivoTrue();
}

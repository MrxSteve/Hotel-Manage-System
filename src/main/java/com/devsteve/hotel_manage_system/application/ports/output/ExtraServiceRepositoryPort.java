package com.devsteve.hotel_manage_system.application.ports.output;

import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;

import java.util.List;
import java.util.Optional;

public interface ExtraServiceRepositoryPort {
    ExtraServiceModel save(ExtraServiceModel model);
    Optional<ExtraServiceModel> findById(Integer id);
    List<ExtraServiceModel> findAll();
    void deleteById(Integer id);
    List<ExtraServiceModel> findAllActive();
}

package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.adapter;

import com.devsteve.hotel_manage_system.application.ports.output.ExtraServiceRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;
import com.devsteve.hotel_manage_system.infra.adapters.output.jpa.repository.ExtraServiceJpaRepository;
import com.devsteve.hotel_manage_system.infra.entities.ExtraService;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ExtraServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExtraServiceAdapter implements ExtraServiceRepositoryPort {
    private final ExtraServiceJpaRepository jpaRepository;
    private final ExtraServiceMapper mapper;

    @Override
    public ExtraServiceModel save(ExtraServiceModel model) {
        ExtraService entity = mapper.modelToEntity(model);
        ExtraService saved = jpaRepository.save(entity);
        return mapper.entityToModel(saved);
    }

    @Override
    public List<ExtraServiceModel> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExtraServiceModel> findAllActive() {
        return jpaRepository.findAllByActivoTrue().stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ExtraServiceModel> findById(Integer id) {
        return jpaRepository.findById(id)
                .map(mapper::entityToModel);
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }
}

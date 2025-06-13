package com.devsteve.hotel_manage_system.application.usecases;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.CreateExtraServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.DeleteExtraServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetActiveExtraServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetAllExtraServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.ExtraServiceRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;

import java.util.List;

public class ExtraServiceService implements
        CreateExtraServiceUseCase,
        GetAllExtraServicesUseCase,
        GetActiveExtraServicesUseCase,
        DeleteExtraServiceUseCase {

    private final ExtraServiceRepositoryPort extraServiceRepositoryPort;

    public ExtraServiceService(ExtraServiceRepositoryPort extraServiceRepositoryPort) {
        this.extraServiceRepositoryPort = extraServiceRepositoryPort;
    }

    @Override
    public ExtraServiceModel create(ExtraServiceModel model) {
        model.setActivo(true);
        return extraServiceRepositoryPort.save(model);
    }

    @Override
    public List<ExtraServiceModel> getAll() {
        return extraServiceRepositoryPort.findAll();
    }

    @Override
    public List<ExtraServiceModel> getActive() {
        return extraServiceRepositoryPort.findAllActive();
    }

    @Override
    public void deleteById(Integer id) {
        extraServiceRepositoryPort.deleteById(id);
    }
}

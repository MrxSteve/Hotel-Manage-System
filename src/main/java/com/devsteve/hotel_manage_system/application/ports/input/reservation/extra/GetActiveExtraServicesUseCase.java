package com.devsteve.hotel_manage_system.application.ports.input.reservation.extra;

import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;

import java.util.List;

public interface GetActiveExtraServicesUseCase {
    List<ExtraServiceModel> getActive();
}

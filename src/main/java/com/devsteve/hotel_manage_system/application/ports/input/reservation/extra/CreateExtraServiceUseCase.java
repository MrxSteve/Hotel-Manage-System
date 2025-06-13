package com.devsteve.hotel_manage_system.application.ports.input.reservation.extra;

import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;

public interface CreateExtraServiceUseCase {
    ExtraServiceModel create(ExtraServiceModel model);
}

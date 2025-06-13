package com.devsteve.hotel_manage_system.shared.mappers.reservation;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;
import com.devsteve.hotel_manage_system.infra.entities.ReservationStatus;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationStatusRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationStatusResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationStatusMapper {
    // Entity <-> Model
    ReservationStatusModel entityToModel(ReservationStatus entity);
    ReservationStatus modelToEntity(ReservationStatusModel model);

    // Model <-> Response
    ReservationStatusModel requestToModel(ReservationStatusRequest request);
    ReservationStatusResponse modelToResponse(ReservationStatusModel model);
}

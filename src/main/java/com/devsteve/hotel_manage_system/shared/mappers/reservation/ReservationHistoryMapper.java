package com.devsteve.hotel_manage_system.shared.mappers.reservation;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;
import com.devsteve.hotel_manage_system.infra.entities.ReservationHistory;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationHistoryRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReservationStatusMapper.class})
public interface ReservationHistoryMapper {
    @Mapping(target = "estadoAnterior", ignore = true)
    @Mapping(target = "estadoNuevo", ignore = true)
    ReservationHistoryModel requestToModel(ReservationHistoryRequest request);

    @Mapping(target = "reservationId", source = "reservation.id")
    ReservationHistoryModel entityToModel(ReservationHistory entity);

    @Mapping(target = "reservation.id", source = "reservationId")
    ReservationHistory modelToEntity(ReservationHistoryModel model);

    ReservationHistoryResponse modelToResponse(ReservationHistoryModel model);

    List<ReservationHistoryResponse> modelListToResponseList(List<ReservationHistoryModel> models);
}

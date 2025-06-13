package com.devsteve.hotel_manage_system.shared.mappers.reservation;

import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;
import com.devsteve.hotel_manage_system.infra.entities.Reservation;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReservationStatusMapper.class})
public interface ReservationMapper {
    // Request -> Model
    @Mapping(target = "id", ignore = true)
    ReservationModel requestToModel(ReservationRequest request);

    // Model -> Response

    ReservationResponse modelToResponse(ReservationModel model);

    List<ReservationResponse> modelListToResponseList(List<ReservationModel> models);

    // Entity -> Model
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "status", source = "status")
    ReservationModel entityToModel(Reservation entity);

    // Model -> Entity
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "room.id", source = "roomId")
    @Mapping(target = "status", source = "status")
    Reservation modelToEntity(ReservationModel model);
}

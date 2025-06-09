package com.devsteve.hotel_manage_system.shared.mappers.room;

import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;
import com.devsteve.hotel_manage_system.infra.entities.RoomStatus;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomStatusRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomStatusResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomStatusMapper {
    // Entity <-> Model
    RoomStatusModel entityToModel(RoomStatus entity);
    RoomStatus modelToEntity(RoomStatusModel model);

    // Model <-> Response
    RoomStatusModel requestToModel(RoomStatusRequest request);
    RoomStatusResponse modelToResponse(RoomStatusModel model);
}

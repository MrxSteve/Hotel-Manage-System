package com.devsteve.hotel_manage_system.shared.mappers.room;

import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;
import com.devsteve.hotel_manage_system.infra.entities.RoomType;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomTypeRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {
    // Entity <-> Model
    RoomTypeModel entityToModel(RoomType entity);
    RoomType modelToEntity(RoomTypeModel model);

    // Model <-> Response
    RoomTypeModel requestToModel(RoomTypeRequest request);
    RoomTypeResponse modelToResponse(RoomTypeModel model);
}

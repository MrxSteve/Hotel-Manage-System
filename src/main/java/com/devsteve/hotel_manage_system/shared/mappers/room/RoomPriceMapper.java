package com.devsteve.hotel_manage_system.shared.mappers.room;

import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;
import com.devsteve.hotel_manage_system.infra.entities.RoomPrice;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomPriceRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomPriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomPriceMapper {
    // DTO <-> Model
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roomType.id", source = "roomTypeId")
    RoomPriceModel requestToModel(RoomPriceRequest request);

    @Mapping(target = "roomTypeId", source = "roomType.id")
    @Mapping(target = "roomTypeNombre", source = "roomType.nombre")
    RoomPriceResponse modelToResponse(RoomPriceModel model);

    // Model <-> Entity
    @Mapping(target = "roomType.id", source = "roomType.id")
    RoomPriceModel entityToModel(RoomPrice entity);

    @Mapping(target = "roomType.id", source = "roomType.id")
    RoomPrice modelToEntity(RoomPriceModel model);
}

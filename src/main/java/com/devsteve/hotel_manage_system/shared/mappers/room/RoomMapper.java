package com.devsteve.hotel_manage_system.shared.mappers.room;

import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;
import com.devsteve.hotel_manage_system.infra.entities.Room;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.room.UpdateRoomRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoomTypeMapper.class, RoomStatusMapper.class, RoomPriceMapper.class, RoomImageMapper.class})
public interface RoomMapper {
    // DTO <-> Model
    @Mapping(target = "roomType.id", source = "roomTypeId")
    @Mapping(target = "status.id", source = "statusId")
    RoomModel requestToModel(RoomRequest request);

    @Mapping(target = "roomType.id", source = "roomTypeId")
    @Mapping(target = "status.id", source = "statusId")
    void updateModelFromRequest(UpdateRoomRequest request, @MappingTarget RoomModel model);

    RoomResponse modelToResponse(RoomModel model);

    List<RoomResponse> modelListToResponseList(List<RoomModel> models);

    // ENTITY <-> MODEL
    Room modelToEntity(RoomModel model);

    RoomModel entityToModel(Room entity);
}

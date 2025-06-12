package com.devsteve.hotel_manage_system.shared.mappers.room;

import com.devsteve.hotel_manage_system.domain.models.room.RoomImageModel;
import com.devsteve.hotel_manage_system.infra.entities.RoomImage;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomImageMapper {
    // Model → Response DTO
    RoomImageResponse modelToResponse(RoomImageModel model);

    List<RoomImageResponse> toResponseList(List<RoomImageModel> models);

    // Entity → Model
    @Mapping(source = "room.id", target = "roomId")
    RoomImageModel entityToModel(RoomImage entity);

    List<RoomImageModel> toModelList(List<RoomImage> entities);

    // Model → Entity
    @Mapping(source = "roomId", target = "room.id")
    RoomImage modelToEntity(RoomImageModel model);

    List<RoomImage> toEntityList(List<RoomImageModel> models);
}

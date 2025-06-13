package com.devsteve.hotel_manage_system.shared.mappers.reservation;

import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;
import com.devsteve.hotel_manage_system.infra.entities.ExtraService;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ExtraServiceRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ExtraServiceResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExtraServiceMapper {
    ExtraServiceModel entityToModel(ExtraService entity);

    ExtraService modelToEntity(ExtraServiceModel model);

    List<ExtraServiceModel> entityListToModelList(List<ExtraService> entities);

    List<ExtraService> modelListToEntityList(List<ExtraServiceModel> models);

    ExtraServiceModel requestToModel(ExtraServiceRequest request);

    ExtraServiceResponse modelToResponse(ExtraServiceModel model);

    List<ExtraServiceResponse> modelListToResponseList(List<ExtraServiceModel> models);
}

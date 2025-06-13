package com.devsteve.hotel_manage_system.shared.mappers.reservation;

import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationServiceModel;
import com.devsteve.hotel_manage_system.infra.entities.ReservationService;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.AssignServiceRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationServiceRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationServiceResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationServiceMapper {

    @Mapping(source = "id.reservationId", target = "reservationId")
    @Mapping(source = "id.serviceId", target = "serviceId")
    ReservationServiceModel entityToModel(ReservationService entity);

    @Mapping(target = "id.reservationId", source = "reservationId")
    @Mapping(target = "id.serviceId", source = "serviceId")
    @Mapping(target = "reservation.id", source = "reservationId")
    @Mapping(target = "service.id", source = "serviceId")
    ReservationService modelToEntity(ReservationServiceModel model);

    ReservationServiceResponse modelToResponse(ReservationServiceModel model);

    List<ReservationServiceResponse> modelListToResponseList(List<ReservationServiceModel> models);

    ReservationServiceModel requestToModel(ReservationServiceRequest request);

    @AfterMapping
    default void enrich(
            ReservationServiceModel model,
            @MappingTarget ReservationServiceResponse response
    ) {
        if (model.getCantidad() != null && model.getPrecioUnitario() != null) {
            response.setTotal(model.getPrecioUnitario().multiply(BigDecimal.valueOf(model.getCantidad())));
        }
        response.setServiceNombre(model.getServiceNombre());
    }
}



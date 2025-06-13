package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.AssignReservationServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetReservationServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.RemoveReservationServiceUseCase;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationServiceModel;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.AssignServiceRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationServiceRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationServiceResponse;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationServiceMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservation-services")
@RequiredArgsConstructor
public class ReservationServiceController {
    private final AssignReservationServicesUseCase assignReservationServicesUseCase;
    private final GetReservationServicesUseCase getReservationServicesUseCase;
    private final RemoveReservationServiceUseCase removeReservationServiceUseCase;
    private final ReservationServiceMapper reservationServiceMapper;

    @PostMapping("/{reservationId}")
    public ResponseEntity<Void> assignServices(
            @PathVariable UUID reservationId,
            @RequestBody @Valid List<AssignServiceRequest> requestList
    ) {
        List<ReservationServiceModel> services = requestList.stream()
                .map(request -> {
                    ReservationServiceModel model = new ReservationServiceModel();
                    model.setReservationId(reservationId);
                    model.setServiceId(request.getServiceId());
                    model.setCantidad(request.getCantidad());
                    return model;
                })
                .toList();

        assignReservationServicesUseCase.assign(reservationId, services);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<List<ReservationServiceResponse>> getServicesByReservation(
            @PathVariable UUID reservationId
    ) {
        List<ReservationServiceModel> models = getReservationServicesUseCase.getByReservationId(reservationId);
        List<ReservationServiceResponse> responses = reservationServiceMapper.modelListToResponseList(models);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{reservationId}/{serviceId}")
    public ResponseEntity<Void> removeServiceFromReservation(
            @PathVariable UUID reservationId,
            @PathVariable Integer serviceId
    ) {
        removeReservationServiceUseCase.remove(reservationId, serviceId);
        return ResponseEntity.noContent().build();
    }
}






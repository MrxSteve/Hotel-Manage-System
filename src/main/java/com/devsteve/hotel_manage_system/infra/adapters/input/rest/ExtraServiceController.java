package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.CreateExtraServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.DeleteExtraServiceUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetActiveExtraServicesUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.extra.GetAllExtraServicesUseCase;
import com.devsteve.hotel_manage_system.domain.models.reservation.ExtraServiceModel;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ExtraServiceRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ExtraServiceResponse;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ExtraServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extra-services")
@RequiredArgsConstructor
public class ExtraServiceController {

    private final CreateExtraServiceUseCase createExtraServiceUseCase;
    private final GetAllExtraServicesUseCase getAllExtraServicesUseCase;
    private final GetActiveExtraServicesUseCase getActiveExtraServicesUseCase;
    private final DeleteExtraServiceUseCase deleteExtraServiceUseCase;
    private final ExtraServiceMapper extraServiceMapper;

    @PostMapping
    public ResponseEntity<ExtraServiceResponse> create(@RequestBody ExtraServiceRequest request) {
        ExtraServiceModel model = extraServiceMapper.requestToModel(request);
        ExtraServiceModel created = createExtraServiceUseCase.create(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(extraServiceMapper.modelToResponse(created));
    }

    @GetMapping
    public List<ExtraServiceResponse> getAll() {
        List<ExtraServiceModel> models = getAllExtraServicesUseCase.getAll();
        return extraServiceMapper.modelListToResponseList(models);
    }

    @GetMapping("/active")
    public List<ExtraServiceResponse> getActive() {
        List<ExtraServiceModel> models = getActiveExtraServicesUseCase.getActive();
        return extraServiceMapper.modelListToResponseList(models);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deleteExtraServiceUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



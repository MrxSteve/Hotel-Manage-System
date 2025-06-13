package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.status.*;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationStatusModel;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationStatusRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationStatusResponse;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationStatusMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservation-status")
@RequiredArgsConstructor
public class ReservationStatusController {
    private final CreateReservationStatusUseCase createReservationStatusUseCase;
    private final DeleteReservationStatusUseCase deleteReservationStatusUseCase;
    private final GetAllReservationStatusUseCase getAllReservationStatusUseCase;
    private final FindByIdReservationStatusUseCase findByIdReservationStatusUseCase;
    private final FindByNameReservationStatusUseCase findByNameReservationStatusUseCase;
    private final ReservationStatusMapper reservationStatusMapper;

    @PostMapping
    public ResponseEntity<ReservationStatusResponse> create(@Valid @RequestBody ReservationStatusRequest request) {
        ReservationStatusModel model = reservationStatusMapper.requestToModel(request);
        ReservationStatusModel created = createReservationStatusUseCase.save(model);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationStatusMapper.modelToResponse(created));
    }

    @GetMapping
    public ResponseEntity<List<ReservationStatusResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ReservationStatusModel> reservationStatuses = getAllReservationStatusUseCase.findAll(page, size);
        List<ReservationStatusResponse> responses = reservationStatuses.stream()
                .map(reservationStatusMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationStatusResponse> findById(@PathVariable Integer id) {
        ReservationStatusModel model = findByIdReservationStatusUseCase.findById(id);
        return ResponseEntity.ok(reservationStatusMapper.modelToResponse(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deleteReservationStatusUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<ReservationStatusResponse> findByName(@RequestParam String name) {
        ReservationStatusModel model = findByNameReservationStatusUseCase.findByName(name);
        return ResponseEntity.ok(reservationStatusMapper.modelToResponse(model));
    }
}

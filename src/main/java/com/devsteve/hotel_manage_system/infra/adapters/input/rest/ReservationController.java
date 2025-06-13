package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.history.GetReservationHistoryByReservationIdUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.reservation.reservations.*;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;
import com.devsteve.hotel_manage_system.domain.models.room.ReservationModel;
import com.devsteve.hotel_manage_system.infra.security.dto.req.ReservationClientRequest;
import com.devsteve.hotel_manage_system.infra.security.services.ClientReservationService;
import com.devsteve.hotel_manage_system.shared.dto.req.reservation.ReservationRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationHistoryResponse;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationResponse;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationHistoryMapper;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final CreateReservationUseCase createReservationUseCase;
    private final GetAllReservationsUseCase getAllReservationsUseCase;
    private final FindReservationByIdUseCase findReservationByIdUseCase;
    private final DeleteReservationUseCase deleteReservationUseCase;
    private final SearchReservationsUseCase searchReservationsUseCase;
    private final ChangeReservationStatusUseCase changeReservationStatusUseCase;
    private final GetReservationHistoryByReservationIdUseCase getReservationHistoryByReservationIdUseCase;
    private final ReservationMapper reservationMapper;
    private final ReservationHistoryMapper reservationHistoryMapper;
    private final ClientReservationService clientReservationService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservationResponse> create(@RequestBody @Valid ReservationRequest request) {
        ReservationModel model = reservationMapper.requestToModel(request);
        ReservationModel saved = createReservationUseCase.save(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationMapper.modelToResponse(saved));
    }

    @PostMapping("/client")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ReservationResponse> createByClient(@RequestBody @Valid ReservationClientRequest request) {
        ReservationModel saved = clientReservationService.createByClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationMapper.modelToResponse(saved));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<ReservationResponse>> getMyReservations() {
        List<ReservationModel> models = clientReservationService.getMyReservations();
        return ResponseEntity.ok(reservationMapper.modelListToResponseList(models));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ReservationModel> list = getAllReservationsUseCase.findAll(page, size);
        return ResponseEntity.ok(reservationMapper.modelListToResponseList(list));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReservationResponse>> search(
            @RequestParam Optional<UUID> userId,
            @RequestParam Optional<UUID> roomId,
            @RequestParam Optional<LocalDate> fechaInicio,
            @RequestParam Optional<LocalDate> fechaFin,
            @RequestParam Optional<Integer> statusId,
            @RequestParam Optional<BigDecimal> totalPagoDesde,
            @RequestParam Optional<BigDecimal> totalPagoHasta,
            @RequestParam Optional<Instant> createdAtDesde,
            @RequestParam Optional<Instant> createdAtHasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ReservationModel> list = searchReservationsUseCase.search(
                userId, roomId, fechaInicio, fechaFin, statusId,
                totalPagoDesde, totalPagoHasta, createdAtDesde, createdAtHasta, page, size
        );
        return ResponseEntity.ok(reservationMapper.modelListToResponseList(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable UUID id) {
        ReservationModel model = findReservationByIdUseCase.findById(id);
        return ResponseEntity.ok(reservationMapper.modelToResponse(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteReservationUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservationResponse> changeStatus(
            @PathVariable UUID id,
            @RequestParam Integer nuevoStatusId,
            @RequestParam(required = false) String comentario
    ) {
        ReservationModel model = changeReservationStatusUseCase.changeStatus(id, nuevoStatusId, comentario);
        return ResponseEntity.ok(reservationMapper.modelToResponse(model));
    }

    @DeleteMapping("/my/{reservationId}")
    public ResponseEntity<Void> cancelMyReservation(@PathVariable UUID reservationId) {
        clientReservationService.cancelMyReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<ReservationHistoryResponse>> getHistory(@PathVariable UUID id) {
        List<ReservationHistoryModel> history = getReservationHistoryByReservationIdUseCase.getHistoryByReservationId(id);
        List<ReservationHistoryResponse> response = reservationHistoryMapper.modelListToResponseList(history);
        return ResponseEntity.ok(response);
    }
}

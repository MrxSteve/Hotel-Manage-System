package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.reservation.history.GetReservationHistoryByReservationIdUseCase;
import com.devsteve.hotel_manage_system.domain.models.reservation.ReservationHistoryModel;
import com.devsteve.hotel_manage_system.shared.dto.res.reservation.ReservationHistoryResponse;
import com.devsteve.hotel_manage_system.shared.mappers.reservation.ReservationHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservation-history")
@RequiredArgsConstructor
public class ReservationHistoryController {
    private final GetReservationHistoryByReservationIdUseCase getReservationHistoryByReservationIdUseCase;
    private final ReservationHistoryMapper reservationHistoryMapper;

    @GetMapping("/{reservationId}")
    public ResponseEntity<List<ReservationHistoryResponse>> getHistoryByReservationId(@PathVariable UUID reservationId) {
        List<ReservationHistoryModel> historyList = getReservationHistoryByReservationIdUseCase.getHistoryByReservationId(reservationId);
        return ResponseEntity.ok(reservationHistoryMapper.modelListToResponseList(historyList));
    }
}

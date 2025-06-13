package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.room.rooms.*;
import com.devsteve.hotel_manage_system.domain.models.room.RoomModel;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.room.UpdateRoomRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomResponse;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final CreateRoomUseCase createRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;
    private final UpdateRoomUseCase updateRoomUseCase;
    private final GetRoomByIdUseCase getRoomByIdUseCase;
    private final GetRoomsByFilterUseCase getRoomsByFilterUseCase;
    private final ListRoomsUseCase listRoomsUseCase;
    private final RoomMapper roomMapper;

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody @Valid RoomRequest request) {
        RoomModel model = roomMapper.requestToModel(request);
        RoomModel saved = createRoomUseCase.create(model);
        return ResponseEntity.ok(roomMapper.modelToResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getById(@PathVariable UUID id) {
        RoomModel model = getRoomByIdUseCase.findById(id);
        return ResponseEntity.ok(roomMapper.modelToResponse(model));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<RoomModel> models = listRoomsUseCase.findAll(page, size);
        return ResponseEntity.ok(roomMapper.modelListToResponseList(models));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable UUID id,
                                               @RequestBody @Valid UpdateRoomRequest request) {
        RoomModel model = getRoomByIdUseCase.findById(id);
        roomMapper.updateModelFromRequest(request, model);
        RoomModel updated = updateRoomUseCase.update(id, model);
        return ResponseEntity.ok(roomMapper.modelToResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteRoomUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RoomResponse>> filter(
            @RequestParam Optional<Integer> numeroHabitacion,
            @RequestParam Optional<Integer> capacidad,
            @RequestParam Optional<Integer> roomTypeId,
            @RequestParam Optional<Integer> statusId,
            @RequestParam Optional<BigDecimal> precioMaximo,
            @RequestParam Optional<LocalDate> fechaReferencia,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<RoomModel> filtered = getRoomsByFilterUseCase.getRoomsByFilter(
                numeroHabitacion,
                capacidad,
                roomTypeId,
                statusId,
                precioMaximo,
                fechaReferencia,
                page,
                size
        );

        return ResponseEntity.ok(roomMapper.modelListToResponseList(filtered));
    }
}

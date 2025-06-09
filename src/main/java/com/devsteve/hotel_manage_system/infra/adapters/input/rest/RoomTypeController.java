package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.room.type.*;
import com.devsteve.hotel_manage_system.domain.models.room.RoomTypeModel;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomTypeRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomTypeResponse;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomTypeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room-types")
@RequiredArgsConstructor
public class RoomTypeController {
    private final CreateRoomTypeUseCase createRoomTypeUseCase;
    private final DeleteRoomTypeUseCase deleteRoomTypeUseCase;
    private final FindByIdRoomTypeUseCase findByIdRoomTypeUseCase;
    private final FindByNameRoomTypeUseCase findByNameRoomTypeUseCase;
    private final GetAllRoomTypeUseCase getAllRoomTypeUseCase;
    private final UpdateRoomTypeUseCase updateRoomTypeUseCase;
    private final RoomTypeMapper roomTypeMapper;

    @PostMapping
    public ResponseEntity<RoomTypeResponse> create(@Valid @RequestBody RoomTypeRequest request) {
        RoomTypeModel model = roomTypeMapper.requestToModel(request);
        RoomTypeModel created = createRoomTypeUseCase.save(model);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomTypeMapper.modelToResponse(created));
    }

    @GetMapping
    public ResponseEntity<List<RoomTypeResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<RoomTypeModel> roomTypes = getAllRoomTypeUseCase.findAll(page, size);
        List<RoomTypeResponse> responses = roomTypes.stream()
                .map(roomTypeMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeResponse> findById(@PathVariable Integer id) {
        RoomTypeModel model = findByIdRoomTypeUseCase.findById(id);
        return ResponseEntity.ok(roomTypeMapper.modelToResponse(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deleteRoomTypeUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<RoomTypeResponse> findByName(@RequestParam String name) {
        RoomTypeModel model = findByNameRoomTypeUseCase.findByName(name);
        return ResponseEntity.ok(roomTypeMapper.modelToResponse(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeResponse> update(@PathVariable Integer id,
                                                   @Valid @RequestBody RoomTypeRequest request) {
        RoomTypeModel model = roomTypeMapper.requestToModel(request);
        RoomTypeModel updated = updateRoomTypeUseCase.update(id, model);
        return ResponseEntity.ok(roomTypeMapper.modelToResponse(updated));
    }

}

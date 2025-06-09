package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.room.status.*;
import com.devsteve.hotel_manage_system.domain.models.room.RoomStatusModel;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomStatusRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomStatusResponse;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomStatusMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room-status")
@RequiredArgsConstructor
public class RoomStatusController {
    private final CreateRoomStatusUseCase createRoomStatusUseCase;
    private final DeleteRoomStatusUseCase deleteRoomStatusUseCase;
    private final GetAllRoomStatusUseCase getAllRoomStatusUseCase;
    private final FindByIdRoomStatusUseCase findByIdRoomStatusUseCase;
    private final FindByNameRoomStatusUseCase findByNameRoomStatusUseCase;
    private final RoomStatusMapper roomStatusMapper;

     @PostMapping
    public ResponseEntity<RoomStatusResponse> create(@Valid @RequestBody RoomStatusRequest request) {
         RoomStatusModel model = roomStatusMapper.requestToModel(request);
         RoomStatusModel created = createRoomStatusUseCase.create(model);

         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(roomStatusMapper.modelToResponse(created));
     }

     @GetMapping
        public ResponseEntity<List<RoomStatusResponse>> getAll(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size
        ) {
         List<RoomStatusModel> roomStatuses = getAllRoomStatusUseCase.findAll(page, size);
         List<RoomStatusResponse> responses = roomStatuses.stream()
                 .map(roomStatusMapper::modelToResponse)
                 .collect(Collectors.toList());
         return ResponseEntity.ok(responses);
     }

     @GetMapping("/{id}")
    public ResponseEntity<RoomStatusResponse> findById(@PathVariable Integer id) {
        RoomStatusModel model = findByIdRoomStatusUseCase.findById(id);
        return ResponseEntity.ok(roomStatusMapper.modelToResponse(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deleteRoomStatusUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<RoomStatusResponse> findByName(@RequestParam String name) {
        RoomStatusModel model = findByNameRoomStatusUseCase.findByName(name);
        return ResponseEntity.ok(roomStatusMapper.modelToResponse(model));
    }
}

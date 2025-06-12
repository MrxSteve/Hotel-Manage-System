package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.room.price.*;
import com.devsteve.hotel_manage_system.domain.models.room.RoomPriceModel;
import com.devsteve.hotel_manage_system.shared.dto.req.room.RoomPriceRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.room.RoomPriceResponse;
import com.devsteve.hotel_manage_system.shared.mappers.room.RoomPriceMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room-prices")
@RequiredArgsConstructor
public class RoomPriceController {
    private final CreateRoomPriceUseCase createRoomPriceUseCase;
    private final DeleteRoomPriceUseCase deleteRoomPriceUseCase;
    private final GetAllRoomPricesUseCase getAllRoomPricesUseCase;
    private final FindRoomPriceByIdUseCase findRoomPriceByIdUseCase;
    private final FindRoomPricesByRoomTypeIdUseCase findRoomPricesByRoomTypeIdUseCase;
    private final RoomPriceMapper roomPriceMapper;

    @PostMapping
    public ResponseEntity<RoomPriceResponse> create(@Valid @RequestBody RoomPriceRequest request) {
        RoomPriceModel model = roomPriceMapper.requestToModel(request);
        RoomPriceModel created = createRoomPriceUseCase.save(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomPriceMapper.modelToResponse(created));
    }

    @GetMapping
    public ResponseEntity<List<RoomPriceResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<RoomPriceModel> models = getAllRoomPricesUseCase.findAll(page, size);
        List<RoomPriceResponse> responses = models.stream()
                .map(roomPriceMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomPriceResponse> findById(@PathVariable Integer id) {
        RoomPriceModel model = findRoomPriceByIdUseCase.findById(id);
        return ResponseEntity.ok(roomPriceMapper.modelToResponse(model));
    }

    @GetMapping("/by-room-type/{roomTypeId}")
    public ResponseEntity<List<RoomPriceResponse>> findByRoomTypeId(@PathVariable Integer roomTypeId) {
        List<RoomPriceModel> models = findRoomPricesByRoomTypeIdUseCase.findByRoomTypeId(roomTypeId);
        List<RoomPriceResponse> responses = models.stream()
                .map(roomPriceMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        deleteRoomPriceUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

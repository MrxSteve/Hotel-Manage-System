package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.CreateRefreshTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.GetRefreshTokenUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.RevokeTokenByUserUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.refresh.RevokeTokenUseCase;
import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.GenerateRefreshTokenRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.RefreshTokenResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.RefreshTokenMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/refresh-tokens")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final GetRefreshTokenUseCase getRefreshTokenUseCase;
    private final RevokeTokenByUserUseCase revokeTokenByUserUseCase;
    private final RevokeTokenUseCase revokeTokenUseCase;
    private final RefreshTokenMapper refreshTokenMapper;

    @PostMapping
    public ResponseEntity<RefreshTokenResponse> createToken(@Valid @RequestBody GenerateRefreshTokenRequest request) {
        RefreshTokenModel model = createRefreshTokenUseCase.createToken(
                request.getUserId(),
                request.getToken(),
                request.getExpirationDate()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(refreshTokenMapper.modelToResponse(model));
    }

    @GetMapping("/by-token")
    public ResponseEntity<RefreshTokenResponse> getByToken(@RequestParam String token) {
        RefreshTokenModel model = getRefreshTokenUseCase.getByToken(token);

        return ResponseEntity.ok(refreshTokenMapper.modelToResponse(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> revokeToken(@PathVariable Integer id) {
        revokeTokenUseCase.revokeToken(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-user/{userId}")
    public ResponseEntity<Void> revokeTokensByUser(@PathVariable UUID userId) {
        revokeTokenByUserUseCase.revokeTokensByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}

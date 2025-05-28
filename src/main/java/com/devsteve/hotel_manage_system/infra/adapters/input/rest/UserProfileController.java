package com.devsteve.hotel_manage_system.infra.adapters.input.rest;

import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.CreateUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.GetUserProfileByUserIdUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.GetUserProfileUseCase;
import com.devsteve.hotel_manage_system.application.ports.input.auth.users.profile.UpdateUserProfileUseCase;
import com.devsteve.hotel_manage_system.domain.models.auth.UserProfileModel;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UpdateUserProfileRequest;
import com.devsteve.hotel_manage_system.shared.dto.req.auth.UserProfileRequest;
import com.devsteve.hotel_manage_system.shared.dto.res.auth.UserProfileResponse;
import com.devsteve.hotel_manage_system.shared.mappers.auth.UserProfileMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {
    private final CreateUserProfileUseCase createUserProfileUseCase;
    private final UpdateUserProfileUseCase updateUserProfileUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final GetUserProfileByUserIdUseCase getUserProfileByUserIdUseCase;
    private final UserProfileMapper userProfileMapper;

    @PostMapping
    public ResponseEntity<UserProfileResponse> createProfile(@Valid @RequestBody UserProfileRequest request) {
        UserProfileModel model = userProfileMapper.requestToModel(request);
        UserProfileModel created = createUserProfileUseCase.create(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileMapper.modelToResponse(created));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<UserProfileResponse> update(
            @PathVariable UUID profileId,
            @Valid @RequestBody UpdateUserProfileRequest request) {

        UserProfileModel model = userProfileMapper.updateRequestToModel(request);
        UserProfileModel updated = updateUserProfileUseCase.update(profileId, model);
        return ResponseEntity.ok(userProfileMapper.modelToResponse(updated));
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfileResponse> getByUserId(@PathVariable UUID profileId) {
        UserProfileModel profile = getUserProfileUseCase.getProfileByUserId(profileId);
        return ResponseEntity.ok(userProfileMapper.modelToResponse(profile));
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<UserProfileResponse> getProfileByUserId(@PathVariable UUID userId) {
        UserProfileModel model = getUserProfileByUserIdUseCase.getUserProfileByUserId(userId);
        return ResponseEntity.ok(userProfileMapper.modelToResponse(model));
    }
}

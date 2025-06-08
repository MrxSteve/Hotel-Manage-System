package com.devsteve.hotel_manage_system.infra.security.services;

import com.devsteve.hotel_manage_system.application.ports.input.auth.user_role.AssignRoleUseCase;
import com.devsteve.hotel_manage_system.application.ports.output.*;
import com.devsteve.hotel_manage_system.domain.models.auth.*;
import com.devsteve.hotel_manage_system.infra.security.dto.req.LoginRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.req.LogoutRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.res.LoginResponse;
import com.devsteve.hotel_manage_system.infra.security.services.oauth.GoogleIdTokenVerifierService;
import com.devsteve.hotel_manage_system.infra.security.utils.JwtTokenProvider;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final CurrentUserService currentUserService;
    private final OAuth2ProviderRepositoryPort oAuth2ProviderRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final UserProfileRepositoryPort userProfileRepositoryPort;
    private final GoogleIdTokenVerifierService googleIdTokenVerifierService;
    private final AssignRoleUseCase assignRoleUseCase;

    private static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);

    public LoginResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = authentication.getName();

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String accessToken = jwtTokenProvider.generateJwtToken(username, roles);

        UserModel user = userRepositoryPort.findByUsername(username)
                .or(() -> userRepositoryPort.findByEmail(username))
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        String refreshTokenString = UUID.randomUUID().toString();

        Instant expirationDate = Instant.now().plus(REFRESH_TOKEN_DURATION);

        RefreshTokenModel refreshToken = new RefreshTokenModel();
        refreshToken.setUserId(user.getId());
        refreshToken.setToken(refreshTokenString);
        refreshToken.setExpirationDate(expirationDate);

        refreshTokenRepositoryPort.save(refreshToken);

        return new LoginResponse(accessToken, refreshTokenString);
    }

    public LoginResponse refreshAccessToken(String refreshTokenString) {

        RefreshTokenModel refreshToken = refreshTokenRepositoryPort.findByToken(refreshTokenString)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpirationDate().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        UserModel user = userRepositoryPort.findById(refreshToken.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + refreshToken.getUserId()));

        List<String> roles = user.getRoles().stream()
                .map(RoleModel::getName)
                .toList();

        String accessToken = jwtTokenProvider.generateJwtToken(user.getUsername(), roles);

        return new LoginResponse(accessToken, refreshTokenString);
    }

    @Transactional
    public void logout(LogoutRequest request) {

        RefreshTokenModel refreshToken = refreshTokenRepositoryPort.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        UserModel currentUser = currentUserService.getCurrentUser();

        if (!refreshToken.getUserId().equals(currentUser.getId())) {
            throw new RuntimeException("You cannot logout another user's token.");
        }

        refreshTokenRepositoryPort.deleteById(refreshToken.getId());
    }

    @Transactional
    public LoginResponse oauth2GoogleLogin(String idTokenString) {

        GoogleIdToken.Payload payload = googleIdTokenVerifierService.verify(idTokenString);

        String provider = "google";
        String providerUserId = payload.getSubject(); // sub
        String email = payload.getEmail();
        String fullName = (String) payload.get("name");
        String picture = (String) payload.get("picture");

        Optional<OAuth2ProviderModel> existingProvider = oAuth2ProviderRepositoryPort
                .findByProviderAndProviderUserId(provider, providerUserId);

        UserModel user;

        if (existingProvider.isPresent()) {
            user = userRepositoryPort.findById(existingProvider.get().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found for OAuth2Provider"));
        } else {
            user = userRepositoryPort.findByEmail(email)
                    .orElse(null);

            if (user == null) {
                user = new UserModel();
                user.setUsername(generateUniqueUsername(email));
                user.setEmail(email);
                user.setPicture(picture);
                user.setEnabled(true);
                user.setMustChangePassword(false);
                user.setPassword(null);

                // Guarda usuario nuevo
                user = userRepositoryPort.save(user);

                // Crea perfil
                UserProfileModel profile = new UserProfileModel();
                profile.setUserId(user.getId());
                profile.setNombreCompleto(fullName);

                userProfileRepositoryPort.save(profile);

                // Asignar rol "ROLE_CLIENTE"
                RoleModel roleCliente = roleRepositoryPort.findByName("ROLE_CLIENTE")
                        .orElseThrow(() -> new RuntimeException("Role ROLE_CLIENTE not found"));

                assignRoleUseCase.assignRole(user.getId(), roleCliente.getId());

                // Guarda OAuth2Provider
                OAuth2ProviderModel providerModel = new OAuth2ProviderModel();
                providerModel.setUserId(user.getId());
                providerModel.setProvider(provider);
                providerModel.setProviderUserId(providerUserId);

                oAuth2ProviderRepositoryPort.save(providerModel);
            }

            user = userRepositoryPort.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found after registration"));
        }

        List<String> roles = user.getRoles().stream()
                .map(RoleModel::getName)
                .toList();

        String accessToken = jwtTokenProvider.generateJwtToken(user.getUsername(), roles);

        String refreshTokenString = UUID.randomUUID().toString();

        Instant expirationDate = Instant.now().plus(REFRESH_TOKEN_DURATION);

        RefreshTokenModel refreshToken = new RefreshTokenModel();
        refreshToken.setUserId(user.getId());
        refreshToken.setToken(refreshTokenString);
        refreshToken.setExpirationDate(expirationDate);

        refreshTokenRepositoryPort.save(refreshToken);

        return new LoginResponse(accessToken, refreshTokenString);
    }

    private String generateUniqueUsername(String email) {
        String base = email.split("@")[0];
        String username = base;

        int counter = 0;
        while (userRepositoryPort.existsByUsername(username)) {
            counter++;
            username = base + counter;
        }

        return username;
    }

}

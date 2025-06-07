package com.devsteve.hotel_manage_system.infra.security.services;

import com.devsteve.hotel_manage_system.application.ports.output.RefreshTokenRepositoryPort;
import com.devsteve.hotel_manage_system.application.ports.output.UserRepositoryPort;
import com.devsteve.hotel_manage_system.domain.models.auth.RefreshTokenModel;
import com.devsteve.hotel_manage_system.domain.models.auth.RoleModel;
import com.devsteve.hotel_manage_system.domain.models.auth.UserModel;
import com.devsteve.hotel_manage_system.infra.security.dto.req.LoginRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.req.LogoutRequest;
import com.devsteve.hotel_manage_system.infra.security.dto.res.LoginResponse;
import com.devsteve.hotel_manage_system.infra.security.utils.JwtTokenProvider;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final CurrentUserService currentUserService;

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

}

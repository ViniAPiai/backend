package com.vini.piai.backend.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.vini.piai.backend.api.access.refreshToken.RefreshTokenService;
import com.vini.piai.backend.api.access.user.User;
import com.vini.piai.backend.security.auth.dto.AuthDtoSignInRequest;
import com.vini.piai.backend.security.auth.dto.AuthDtoSignInResponse;
import com.vini.piai.backend.security.jwt.JwtService;

import java.time.Instant;

@Service
public class AuthService {

    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(RefreshTokenService refreshTokenService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthDtoSignInResponse signIn(AuthDtoSignInRequest dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.email(), dto.password()
        ));
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            String token = jwtService.generateToken(user);
            refreshTokenService.createOrUpdateRefreshToken(user.getEmail(), token,
                    Instant.now().plusSeconds(jwtService.getExpirationTime()));
            return new AuthDtoSignInResponse(token, Instant.now().plusSeconds(jwtService.getExpirationTime()));
        }else {
            throw new RuntimeException("Authentication failed");
        }
    }

}

package com.vini.piai.backend.api.access.refreshToken;

import org.springframework.stereotype.Service;
import com.vini.piai.backend.api.access.user.UserService;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserService userService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
    }

    public RefreshToken createOrUpdateRefreshToken(String email, String token, Instant expiryDate) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByUserEmail(email);
        if(refreshTokenOptional.isPresent()){
            RefreshToken refreshToken = refreshTokenOptional.get();
            refreshToken.setToken(token);
            refreshToken.setExpireIn(expiryDate);
            return refreshTokenRepository.save(refreshToken);
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userService.findByEmail(email))
                .token(token)
                .expireIn(expiryDate)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow();
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpireIn().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

}

package com.vini.piai.backend.api.access.refreshToken;

import com.vini.piai.backend.api.access.refreshToken.dto.RefreshTokenDtoRequest;
import com.vini.piai.backend.api.access.user.User;
import com.vini.piai.backend.api.access.user.UserService;
import com.vini.piai.backend.api.utils.ExceptionEnum;
import com.vini.piai.backend.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    private final JwtService jwtService;

    public void createOrUpdate(User user) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByUser(user);
        String token = jwtService.generateRefreshToken(user);
        if(optional.isPresent()) {
            RefreshToken refreshToken = optional.get();
            refreshToken.setToken(token);
            refreshToken.setExpireIn(jwtService.extractExpiration(token).toInstant());
            refreshTokenRepository.save(refreshToken);
            return;
        }
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setExpireIn(jwtService.extractExpiration(token).toInstant());
        refreshToken.setUser(user);
        refreshTokenRepository.save(refreshToken);
    }

    public RefreshTokenDtoRequest refreshToken(RefreshTokenDtoRequest dto) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByToken(dto.token());
        if(optional.isPresent()){
            if (optional.get().getExpireIn().compareTo(Instant.now()) < 0) {
                refreshTokenRepository.delete(optional.get());
                throw new RuntimeException(ExceptionEnum.REFRESH_TOKEN_IS_EXPIRED_PLEASE_LOGIN.getTopic());
            }
            User user = userService.findByEmail(jwtService.extractUsername(optional.get().getToken()));
            return new RefreshTokenDtoRequest(jwtService.generateToken(user));
        }
        throw new RuntimeException(ExceptionEnum.REFRESH_TOKEN_IS_EXPIRED_PLEASE_LOGIN.getTopic());
    }

}

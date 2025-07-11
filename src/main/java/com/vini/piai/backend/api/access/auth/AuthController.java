package com.vini.piai.backend.api.access.auth;

import com.vini.piai.backend.api.access.refreshToken.RefreshTokenService;
import com.vini.piai.backend.api.access.refreshToken.dto.RefreshTokenDtoRequest;
import com.vini.piai.backend.api.access.user.dto.UserDtoInsert;
import com.vini.piai.backend.api.access.user.dto.UserDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vini.piai.backend.api.access.auth.dto.AuthDtoSignInRequest;
import com.vini.piai.backend.api.access.auth.dto.AuthDtoSignInResponse;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(path = "/sign_in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthDtoSignInResponse> signIn(@RequestBody AuthDtoSignInRequest dto) {
        return ResponseEntity.ok(authService.signIn(dto));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDtoResponse> signUp(@RequestBody UserDtoInsert dto) {
        return ResponseEntity.ok(authService.signUpCommonUser(dto).getDtoResponse());
    }

    @PostMapping(path = "/refresh_token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RefreshTokenDtoRequest> refreshToken(@RequestBody RefreshTokenDtoRequest request) {
        return ResponseEntity.ok(refreshTokenService.refreshToken(request));
    }

}

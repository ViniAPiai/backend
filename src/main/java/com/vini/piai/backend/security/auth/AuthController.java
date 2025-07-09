package com.vini.piai.backend.security.auth;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vini.piai.backend.security.auth.dto.AuthDtoSignInRequest;
import com.vini.piai.backend.security.auth.dto.AuthDtoSignInResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/sign_in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthDtoSignInResponse> signIn(@RequestBody AuthDtoSignInRequest request) {
        return ResponseEntity.ok(authService.signIn(request));
    }

}

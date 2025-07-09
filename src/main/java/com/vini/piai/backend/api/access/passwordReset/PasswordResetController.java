package com.vini.piai.backend.api.access.passwordReset;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vini.piai.backend.api.access.passwordReset.dto.*;

@RestController
@RequestMapping(path = "/api/password_reset")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping(path = "/email", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordResetDtoEmailResponse> passwordResetEmail(
            @Valid @RequestBody PasswordResetDtoEmailRequest dto) {
        return ResponseEntity.ok(passwordResetService.passwordResetEmail(dto));
    }

    @PostMapping(path = "/code", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordResetDtoCodeResponse> passwordResetCode(
            @Valid @RequestBody PasswordResetDtoCodeRequest dto) {
        return ResponseEntity.ok(passwordResetService.passwordResetCode(dto));
    }

    @PostMapping(path = "/redefinition", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordResetDtoResponse> passwordReset(
            @Valid @RequestBody PasswordResetDtoRequest dto, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(passwordResetService.passwordReset(dto, token));
    }

}

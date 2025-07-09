package com.vini.piai.backend.api.access.passwordReset.dto;

import lombok.Builder;
import com.vini.piai.backend.api.access.passwordReset.PasswordReset;

import java.io.Serializable;

/**
 * DTO for {@link PasswordReset}
 */
@Builder
public record PasswordResetDtoCodeResponse(String token, String email) implements Serializable {

    @Override
    public String token() {
        return token;
    }

    @Override
    public String email() {
        return email;
    }

}
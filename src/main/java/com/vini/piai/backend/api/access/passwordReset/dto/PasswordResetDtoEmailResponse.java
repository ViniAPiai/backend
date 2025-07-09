package com.vini.piai.backend.api.access.passwordReset.dto;

import lombok.Builder;
import com.vini.piai.backend.api.access.passwordReset.PasswordReset;

import java.io.Serializable;

/**
 * DTO for {@link PasswordReset}
 * @param message message generated for the user reset his password
 */
@Builder
public record PasswordResetDtoEmailResponse(String message) implements Serializable {
}
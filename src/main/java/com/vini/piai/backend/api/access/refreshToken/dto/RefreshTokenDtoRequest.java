package com.vini.piai.backend.api.access.refreshToken.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RefreshTokenDtoRequest(
        @NotNull(message = "token cannot be null")
        @NotBlank(message = "token cannot be blank")
        String token
) { }

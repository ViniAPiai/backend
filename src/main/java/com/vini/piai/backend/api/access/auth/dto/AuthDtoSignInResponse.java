package com.vini.piai.backend.api.access.auth.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AuthDtoSignInResponse(String token, Instant expiresIn) {

}

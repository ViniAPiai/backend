package com.vini.piai.backend.security.auth.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AuthDtoSignInResponse(String token, Instant expiresIn) {

}

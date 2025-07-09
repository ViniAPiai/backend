package com.vini.piai.backend.security.auth.dto;

import lombok.Builder;

@Builder
public record AuthDtoSignInRequest(String email, String password) {
}

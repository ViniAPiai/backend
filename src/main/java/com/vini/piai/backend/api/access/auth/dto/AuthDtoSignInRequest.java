package com.vini.piai.backend.api.access.auth.dto;

import lombok.Builder;

@Builder
public record AuthDtoSignInRequest(String email, String password) {
}

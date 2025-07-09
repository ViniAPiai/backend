package com.vini.piai.backend.api.access.passwordReset.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PasswordResetDtoResponse(
        @NotNull(message = "O campo \"Mensagem\" não pode conter um valor nulo")
        @NotBlank(message = "O campo \"Mensagem\" não pode conter um valor vazio")
        String message) {

    @Override
    public String message() {
        return message;
    }
}

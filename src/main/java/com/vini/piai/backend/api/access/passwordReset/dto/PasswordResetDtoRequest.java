package com.vini.piai.backend.api.access.passwordReset.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PasswordResetDtoRequest(
        @NotNull(message = "O campo \"E-mail\" não pode conter um valor nulo")
        @NotBlank(message = "O campo \"E-mail\" não pode conter um valor vazio")
        String email,
        @NotNull(message = "O campo \"Código\" não pode conter um valor nulo")
        @NotBlank(message = "O campo \"Código\" não pode conter um valor vazio")
        String code,
        @NotNull(message = "O campo \"Senha\" não pode conter um valor nulo")
        @NotBlank(message = "O campo \"Senha\" não pode conter um valor vazio")
        String password) {

    @Override
    public String email() {
        return email;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String password() {
        return password;
    }
}

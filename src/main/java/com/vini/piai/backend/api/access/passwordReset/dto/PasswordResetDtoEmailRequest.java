package com.vini.piai.backend.api.access.passwordReset.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PasswordResetDtoEmailRequest(@NotNull(message = "O campo \"E-mail\" não pode conter um valor nulo")
                                           @NotBlank(message = "O campo \"E-mail\" não pode conter um valor vazio")
                                           String email) {

    @Override
    public String email() {
        return email;
    }

}

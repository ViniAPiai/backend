package com.vini.piai.backend.api.access.passwordReset.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.vini.piai.backend.api.access.passwordReset.PasswordReset;

import java.io.Serializable;

/**
 * DTO for {@link PasswordReset}
 */

public record PasswordResetDtoCodeRequest(@NotNull(message = "O campo \"código\" não pode conter um valor nulo")
                                          @NotBlank(message = "O campo \"código\" não pode conter um valor vazio")
                                          String code,
                                          @NotNull(message = "O campo \"E-mail\" não pode conter um valor nulo")
                                          @NotBlank(message = "O campo \"E-mail\" não pode conter um valor vazio")
                                          String email) implements Serializable { }
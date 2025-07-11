package com.vini.piai.backend.api.access.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.vini.piai.backend.api.access.user.User}
 */
public record UserDtoInsert(@NotBlank String fullName, @Email @NotBlank String email, @NotBlank String documentNumber,
                            @NotBlank @NotNull String password) implements Serializable {

}
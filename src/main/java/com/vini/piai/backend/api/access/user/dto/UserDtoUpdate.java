package com.vini.piai.backend.api.access.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.vini.piai.backend.api.access.user.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoUpdate implements Serializable {

    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String documentNumber;

}
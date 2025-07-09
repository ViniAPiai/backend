package com.vini.piai.backend.api.access.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.vini.piai.backend.api.access.user.User}
 */
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoInsert implements Serializable {

    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String documentNumber;

}
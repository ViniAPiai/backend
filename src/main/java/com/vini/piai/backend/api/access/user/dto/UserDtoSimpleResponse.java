package com.vini.piai.backend.api.access.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.vini.piai.backend.api.access.user.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoSimpleResponse implements Serializable {
    @NotBlank
    private UUID uuid;
    @NotBlank
    private String fullName;
}
package com.vini.piai.backend.api.access.user.dto;

import lombok.*;
import com.vini.piai.backend.api.access.role.RoleEnum;
import com.vini.piai.backend.api.access.user.User;
import com.vini.piai.backend.api.utils.MessageResponse;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {

    private UUID uuid;
    private String fullName;
    private String email;
    private String documentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleEnum roleName;

}
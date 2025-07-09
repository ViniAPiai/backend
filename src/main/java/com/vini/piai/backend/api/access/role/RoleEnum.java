package com.vini.piai.backend.api.access.role;

import lombok.Getter;
import com.vini.piai.backend.api.access.privilege.PrivilegeEnum;

import java.util.List;

@Getter
public enum RoleEnum {

    USER("Usuário base do sistema", List.of()),
    ADMIN("Administrador do sistema", List.of()),
    ANONYMOUS("Usuário anonimo", List.of());

    private final String description;
    private final List<PrivilegeEnum> privileges;

    RoleEnum(String description, List<PrivilegeEnum> privileges) {
        this.description = description;
        this.privileges = privileges;
    }

}

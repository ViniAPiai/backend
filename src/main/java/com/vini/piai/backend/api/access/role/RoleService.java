package com.vini.piai.backend.api.access.role;

import com.vini.piai.backend.api.utils.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Method for finding a role
     * @param name the role to be found
     * @return the role founded
     * @throws RuntimeException the role was not founded
     */
    public Role findByName(RoleEnum name) throws RuntimeException {
        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException(ExceptionEnum.ROLE_NOT_FOUND.getTopic()));
    }
}

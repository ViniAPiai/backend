package com.vini.piai.backend.api.access.role;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(RoleEnum name) {
        return roleRepository.findByName(name).orElseThrow();
    }
}

package com.vini.piai.backend.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.vini.piai.backend.api.access.passwordReset.PasswordReset;
import com.vini.piai.backend.api.access.passwordReset.PasswordResetRepository;
import com.vini.piai.backend.api.access.role.Role;
import com.vini.piai.backend.api.access.role.RoleEnum;
import com.vini.piai.backend.api.access.role.RoleRepository;
import com.vini.piai.backend.api.access.user.User;
import com.vini.piai.backend.api.access.user.UserRepository;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordResetRepository passwordResetRepository;
    private final PasswordEncoder passwordEncoder;

    public Bootstrap(UserRepository userRepository, RoleRepository roleRepository,
                     PasswordResetRepository passwordResetRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordResetRepository = passwordResetRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.seedRoles();
        this.seedUsers();
    }

    private void seedRoles() {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            Optional<Role> roleOptional = roleRepository.findByName(roleEnum);
            if (roleOptional.isEmpty()) {
                roleRepository.save(Role.builder().name(roleEnum).description(roleEnum.getDescription()).build());
            }
        }
    }

    private void seedUsers() {
        List<String> emails = List.of("test@gmail.com");
        List<String> name = List.of("Vinicius");
        List<String> passwords = List.of("V!nicius99");
        List<String> documentNumbers = List.of("123.412.312-50");
        Optional<Role> optRole = roleRepository.findByName(RoleEnum.USER);
        for (int i = 0; i < emails.size(); i++) {
            Optional<User> opt = userRepository.findByEmail(emails.get(i));
            if (opt.isEmpty() && optRole.isPresent()) {
                userRepository.save(User.builder()
                        .email(emails.get(i))
                        .fullName(name.get(i))
                        .password(passwordEncoder.encode(passwords.get(i)))
                        .documentNumber(documentNumbers.get(i))
                        .role(optRole.get())
                        .build());
            }
        }
    }

}

package com.vini.piai.backend.api.access.passwordReset;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.vini.piai.backend.api.access.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "password_reset")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    private String code;

    @NotNull
    private LocalDateTime timeLimit;

    @OneToOne
    private User user;

    @PrePersist
    private void prePersist() {
        this.timeLimit = LocalDateTime.now().plusMinutes(5);
    }

    @PreUpdate
    private void preUpdate() {
        this.timeLimit = LocalDateTime.now().plusMinutes(5);
    }


}
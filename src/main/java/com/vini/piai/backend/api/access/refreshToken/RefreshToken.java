package com.vini.piai.backend.api.access.refreshToken;

import jakarta.persistence.*;
import lombok.*;
import com.vini.piai.backend.api.access.user.User;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "refresh_token")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String token;

    private Instant expireIn;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;


}
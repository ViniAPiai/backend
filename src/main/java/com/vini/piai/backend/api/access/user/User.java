package com.vini.piai.backend.api.access.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.vini.piai.backend.api.access.role.Role;
import com.vini.piai.backend.api.access.user.dto.UserDtoResponse;
import com.vini.piai.backend.api.access.user.dto.UserDtoSimpleResponse;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @NotBlank
    @Email
    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String documentNumber;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean enable;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @PrePersist
    private void prePersist() {
        this.enable = true;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName().name());
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public UserDtoResponse getDtoResponse() {
        return UserDtoResponse.builder()
                .uuid(getUuid())
                .fullName(getFullName())
                .email(getEmail())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .roleName(getRole().getName())
                .build();
    }

    public UserDtoSimpleResponse getDtoSimpleResponse() {
        return UserDtoSimpleResponse.builder()
                .uuid(getUuid())
                .fullName(getFullName())
                .build();
    }

}
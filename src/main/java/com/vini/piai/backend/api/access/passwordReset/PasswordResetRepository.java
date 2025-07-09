package com.vini.piai.backend.api.access.passwordReset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {

    @Query(value = "SELECT pr FROM PasswordReset pr WHERE pr.user.email = :email")
    Optional<PasswordReset> findByUserEmail(@Param("email") String email);

}
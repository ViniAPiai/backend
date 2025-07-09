package com.vini.piai.backend.api.access.user;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> , JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);

    Optional<User> findByDocumentNumber(String documentNumber);
}
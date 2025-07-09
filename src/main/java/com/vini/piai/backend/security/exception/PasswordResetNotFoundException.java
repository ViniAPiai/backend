package com.vini.piai.backend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordResetNotFoundException extends RuntimeException{

    public PasswordResetNotFoundException(String email) {
        super("Redefinição de Senha não encontrada com o e-mail " + email);
    }

}

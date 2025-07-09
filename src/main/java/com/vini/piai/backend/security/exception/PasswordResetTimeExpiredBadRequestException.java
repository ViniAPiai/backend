package com.vini.piai.backend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordResetTimeExpiredBadRequestException extends RuntimeException{

    public PasswordResetTimeExpiredBadRequestException(String email) {
        super("Tempo limite expirado para o código gerado par ao e-mail " + email);
    }

}

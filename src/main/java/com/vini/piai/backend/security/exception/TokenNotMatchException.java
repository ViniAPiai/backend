package com.vini.piai.backend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class TokenNotMatchException extends RuntimeException{

    public TokenNotMatchException() {
        super("Token não compatível com o e-mail em que foi gerado");
    }

}

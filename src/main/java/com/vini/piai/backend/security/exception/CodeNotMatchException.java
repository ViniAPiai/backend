package com.vini.piai.backend.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CodeNotMatchException extends RuntimeException{

    public CodeNotMatchException(String email) {
        super("Código para o e-mail " + email + " não compatível");
    }

}
